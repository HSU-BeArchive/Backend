package com.likelion.moamoa.domain.folder.web.dto;

public record ModifyFolderRes(
        Long userId,
        Long folderId,
        String folderNameBefore,
        String folderNameAfter
) {
}
