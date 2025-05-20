package com.likelion.moamoa.domain.reference.service;

import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.folder.exception.NotFoundFolderException;
import com.likelion.moamoa.domain.folder.repository.FolderRepository;
import com.likelion.moamoa.domain.reference.entity.Reference;
import com.likelion.moamoa.domain.reference.repository.ReferenceRepository;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceReq;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

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
            /*
            스프링에서 `폼(form)` 데이터나 `multipart/form-data` 요청을 받을 때 사용
            HTTP 요청의 파라미터(name, description 등) 를 자동으로 DTO 객체에 바인딩해주는 어노테이션
            주로 form-data, query string, multipart/form-data 요청에서 사용
            내부적으로는 @RequestParam과 동일한 방식으로 동작하지만, 객체 단위로 데이터를 자동으로 매핑
           */
    ) {
        // folerId -> folder 확인
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        // s3에 저장된 이미지 url 받아오기
        String url = imageService.uploadImageToS3(saveReferenceReq.getImg());

        // reference 저장
        Reference reference = Reference.builder()
                .folder(folder)
                .name(saveReferenceReq.getName())
                .description(saveReferenceReq.getDescription())
                .imgUrl(url)
                .build();

        Reference saveReference = referenceRepository.save(reference);

        return new SaveReferenceRes(
                saveReference.getFolder().getFolderId(),
                saveReference.getId(),
                saveReference.getName(),
                saveReference.getDescription(),
                saveReference.getImgUrl()
        );
    }
}
