package com.likelion.moamoa.domain.folder.web.dto;

public record CreateFolderRes(
        Long userId,
        Long folderId,
        String folderName,
        Long folderOrder
) {
}
