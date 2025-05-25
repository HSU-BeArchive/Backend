package com.likelion.moamoa.domain.folder.web.dto;

public record ChangeFolderRes(
        Long userId,
        Long folderId,
        Long folderOrderBefore,
        Long folderOrderAfter
) {
}
