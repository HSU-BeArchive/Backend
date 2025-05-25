package com.likelion.moamoa.domain.folder.web.dto;

import java.util.List;

public record FolderSummaryRes(
        Long userId,
        List<FolderSummary> folderSummeryList
) {
    public record FolderSummary(
            Long folderId,
            String folderName,
            Long folderOrder
    ) {
    }
}
