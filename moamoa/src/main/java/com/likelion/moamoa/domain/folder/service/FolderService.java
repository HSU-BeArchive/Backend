package com.likelion.moamoa.domain.folder.service;

import com.likelion.moamoa.domain.folder.web.dto.CreateFolderReq;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderRes;
import com.likelion.moamoa.domain.folder.web.dto.FolderSummeryRes;

public interface FolderService {
    // 폴더 생성
    CreateFolderRes createFolder(Long userId, CreateFolderReq createFolderReq);

    // 폴더 전체 조회
    FolderSummeryRes getAllByFolder(Long userId);

    // 폴더 삭제
    void deleteOneFolder(Long userId, Long folderid);
}
