package com.likelion.moamoa.domain.folder.service;

import com.likelion.moamoa.domain.folder.web.dto.CreateFolderReq;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderRes;

public interface FolderService {
    // 폴더 생성
    CreateFolderRes createFolder(Long userId, CreateFolderReq createFolderReq);
}
