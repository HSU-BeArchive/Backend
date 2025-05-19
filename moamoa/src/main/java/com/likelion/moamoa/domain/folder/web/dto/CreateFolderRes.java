package com.likelion.moamoa.domain.folder.web.dto;

public record CreateFolderRes(
    Long folderId,
    String folderName,
    String folderColor,
    Long userId
) {
}
