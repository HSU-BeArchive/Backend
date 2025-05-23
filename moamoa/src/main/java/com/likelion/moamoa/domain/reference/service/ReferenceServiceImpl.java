package com.likelion.moamoa.domain.reference.service;

import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.folder.exception.NotFoundFolderException;
import com.likelion.moamoa.domain.folder.repository.FolderRepository;
import com.likelion.moamoa.domain.reference.entity.Reference;
import com.likelion.moamoa.domain.reference.exception.DuplicateImgNameException;
import com.likelion.moamoa.domain.reference.repository.ReferenceRepository;
import com.likelion.moamoa.domain.reference.web.dto.ReferenceSummaryRes;
import com.likelion.moamoa.domain.reference.web.dto.ReferenceSummaryRes.ReferenceSummary;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceReq;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {

    private final FolderRepository folderRepository;
    private final ReferenceRepository referenceRepository;

    private final ImageService imageService;

    // 래퍼런스 저장
    @Override
    public SaveReferenceRes saveReference(
            Long folderId,
            @ModelAttribute SaveReferenceReq saveReferenceReq
    ) {
        // folerId -> folder 확인
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        // 파일명 중복 확인
        if (referenceRepository.existsByName(saveReferenceReq.getName())) {
            throw new DuplicateImgNameException();
        }

        // s3에 저장된 이미지 url 받아오기
        String url = imageService.uploadImageToS3(saveReferenceReq.getImg());

        // reference 저장
        Reference reference = Reference.builder()
                .folder(folder)
                .name(saveReferenceReq.getName())
                .description(saveReferenceReq.getDescription())
                .imgUrl(url)
                .referenceOrder(referenceRepository.count()) // 0번부터 시작
                .build();

        Reference saveReference = referenceRepository.save(reference);

        return new SaveReferenceRes(
                saveReference.getFolder().getFolderId(),
                saveReference.getReferenceId(),
                saveReference.getName(),
                saveReference.getDescription(),
                saveReference.getImgUrl(),
                saveReference.getReferenceOrder()
        );
    }

    // 래퍼런스 전체 조회
    @Override
    public ReferenceSummaryRes getAllReference(Long folderId) {
        // folerId -> folder 확인
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        List<Reference> references = referenceRepository.findAll();
        List<ReferenceSummary> referenceSummaryList = new ArrayList<>();

        for (Reference reference : references) {
            ReferenceSummary referenceSummary = new ReferenceSummary(
                    reference.getReferenceId(),
                    reference.getReferenceOrder(),
                    reference.getImgUrl()
            );
            referenceSummaryList.add(referenceSummary);
        }

        return new ReferenceSummaryRes(referenceSummaryList);
    }
}
