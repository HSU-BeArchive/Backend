package com.likelion.moamoa.domain.folder.web.controller;

import com.likelion.moamoa.domain.folder.service.FolderService;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderReq;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/folders")
@RequiredArgsConstructor
public class FolderController {
    // 의존성 부여
    private final FolderService folderService;

    // 폴더 생성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> CreateFolder(@PathVariable Long userId, @RequestBody @Valid CreateFolderReq createFolderReq) {

        CreateFolderRes createFolderRes = folderService.createFolder(userId,createFolderReq);


        return ResponseEntity.ok(SuccessResponse.created(createFolderRes));
    }
}
