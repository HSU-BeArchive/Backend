package com.likelion.moamoa.domain.folder.web.controller;

import com.likelion.moamoa.domain.folder.service.FolderService;
import com.likelion.moamoa.domain.folder.web.dto.*;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/folder")
@RequiredArgsConstructor
public class FolderController {
    // 의존성 부여
    private final FolderService folderService;

    // 폴더 생성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> CreateFolder(
            @PathVariable Long userId,
            @RequestBody @Valid CreateFolderReq createFolderReq
    ) {

        CreateFolderRes createFolderRes = folderService.createFolder(userId, createFolderReq);

        return ResponseEntity.ok(SuccessResponse.created(createFolderRes));
    }

    // 폴더 전체 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllByFolder(@PathVariable Long userId) {
        FolderSummaryRes folderSummeryRes = folderService.getAllByFolder(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(folderSummeryRes));
    }

    // 폴더 이름 수정
    @PutMapping("/{folderId}")
    public ResponseEntity<SuccessResponse<?>> modifyFolder(
            @PathVariable Long userId,
            @PathVariable Long folderId,
            @RequestBody @Valid ModifyFolderReq modifyFolderReq
    ) {
        ModifyFolderRes modifyFolderRes = folderService.modifyFolderName(userId, folderId, modifyFolderReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(modifyFolderRes));
    }

    // 폴더 삭제
    @DeleteMapping("/{folderId}")
    public ResponseEntity<SuccessResponse<?>> deleteOneFolder(
            @PathVariable Long userId,
            @PathVariable Long folderId
    ) {
        folderService.deleteOneFolder(userId, folderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
    }

    // 폴더 위치 순서 변경
    @PutMapping("/{folderId}/order")
    public ResponseEntity<SuccessResponse<?>> changeFolderOrder(
            @PathVariable Long userId,
            @PathVariable Long folderId,
            @RequestBody @Valid ChangeFolderReq changeFolderReq
    ) {
        ChangeFolderRes changeFolderRes = folderService.changeFolderOrder(userId, folderId, changeFolderReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(changeFolderRes));
    }
}
