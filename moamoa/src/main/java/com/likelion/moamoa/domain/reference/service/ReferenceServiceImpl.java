package com.likelion.moamoa.domain.reference.service;

import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.folder.exception.NotFoundFolderException;
import com.likelion.moamoa.domain.folder.repository.FolderRepository;
import com.likelion.moamoa.domain.reference.entity.Reference;
import com.likelion.moamoa.domain.reference.exception.DuplicateImgNameException;
import com.likelion.moamoa.domain.reference.exception.NotFoundReferenceException;
import com.likelion.moamoa.domain.reference.repository.ReferenceRepository;
import com.likelion.moamoa.domain.reference.web.dto.ReferenceDetailRes;
import com.likelion.moamoa.domain.reference.web.dto.ReferenceSummaryRes;
import com.likelion.moamoa.domain.reference.web.dto.ReferenceSummaryRes.ReferenceSummary;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceReq;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (referenceRepository.existsByName(saveReferenceReq.getReferenceName())) {
            throw new DuplicateImgNameException();
        }

        // s3에 저장된 이미지 url 받아오기
        String url = imageService.uploadImageToS3(saveReferenceReq.getReferenceImg());


        // reference 저장
        Reference reference = Reference.builder()
                .folder(folder)
                .name(saveReferenceReq.getReferenceName())
                .description(saveReferenceReq.getReferenceDescription())
                .imgUrl(url)
                .referenceOrder(referenceRepository.countReferenceByFolder_FolderId(folderId)) // 0번부터 시작
                .build();

        Reference saveReference = referenceRepository.save(reference);

        return new SaveReferenceRes(
                saveReference.getReferenceId(),
                saveReference.getName(),
                saveReference.getDescription(),
                saveReference.getImgUrl(),
                saveReference.getReferenceOrder(),
                saveReference.getFolder().getFolderId()
        );
    }

    // 래퍼런스 전체 조회
    @Override
    public ReferenceSummaryRes getAllReference(Long folderId) {
        // folerId -> folder 확인
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        List<Reference> references = referenceRepository.findAllByFolder_FolderId(folderId);
        List<ReferenceSummary> referenceSummaryList = new ArrayList<>();

        for (Reference reference : references) {
            ReferenceSummary referenceSummary = new ReferenceSummary(
                    reference.getReferenceId(),
                    reference.getName(),
                    reference.getReferenceOrder(),
                    reference.getImgUrl()
            );
            referenceSummaryList.add(referenceSummary);
        }

        return new ReferenceSummaryRes(folder.getFolderId(), referenceSummaryList);
    }

    // 래퍼런스 단일 조회
    @Override
    public ReferenceDetailRes getReference(Long folderId, Long referenceId) {
        Reference reference = referenceRepository.findById(referenceId)
                .orElseThrow(NotFoundReferenceException::new);

        if (!reference.getFolder().getFolderId().equals(folderId)) {
            throw new NotFoundFolderException();
        }

        return new ReferenceDetailRes(
                reference.getReferenceId(),
                reference.getName(),
                reference.getDescription(),
                reference.getImgUrl(),
                reference.getReferenceOrder(),
                reference.getReferenceOrder()
        );
    }

    // 래퍼런스 삭제
    @Transactional
    @Override
    public void deleteReference(Long folderId, Long referenceId) {
        Reference reference = referenceRepository.findById(referenceId)
                .orElseThrow(NotFoundReferenceException::new);

        if (!reference.getFolder().getFolderId().equals(folderId)) {
            throw new NotFoundFolderException();
        }

        // 이미지 S3에서 삭제
        imageService.deleteImageFromS3(reference.getImgUrl());

        referenceRepository.delete(reference);
    }
}
