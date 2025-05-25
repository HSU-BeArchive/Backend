package com.likelion.moamoa.domain.folder.service;

import com.likelion.moamoa.domain.folder.web.dto.*;

public interface FolderService {
    // 폴더 생성
    CreateFolderRes createFolder(Long userId, CreateFolderReq createFolderReq);

    // 폴더 전체 조회
    FolderSummaryRes getAllByFolder(Long userId);

    // 폴더 이름 변경
    ModifyFolderRes modifyFolderName(Long userId, Long folderId, ModifyFolderReq modifyFolderReq);

    // 폴더 삭제
    void deleteOneFolder(Long userId, Long folderid);

    // 폴더 순서 변경
    ChangeFolderRes changeFolderOrder(Long userId, Long folderId, ChangeFolderReq changeFolderReq);
}
