package com.likelion.moamoa.domain.reference.web.controller;

import com.likelion.moamoa.domain.folder.web.dto.CreateFolderReq;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderRes;
import com.likelion.moamoa.domain.folder.web.dto.FolderSummeryRes;
import com.likelion.moamoa.domain.reference.service.ReferenceService;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceReq;
import com.likelion.moamoa.domain.reference.web.dto.SaveReferenceRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folder/{folderId}/reference")
@RequiredArgsConstructor
public class ReferenceController {
    // 의존성 부여
    private final ReferenceService referenceService;

    // 사진 저장
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> SaveReference(
            @PathVariable Long folderId,
            @ModelAttribute @Valid SaveReferenceReq saveReferenceReq
    ) {
        SaveReferenceRes saveReferenceRes = referenceService.saveReference(folderId, saveReferenceReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(saveReferenceRes));
    }
}
