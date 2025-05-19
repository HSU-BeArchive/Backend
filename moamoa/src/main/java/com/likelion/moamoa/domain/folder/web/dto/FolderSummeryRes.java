package com.likelion.moamoa.domain.folder.web.dto;

import java.util.List;

public record FolderSummeryRes(List<FolderSummery> folderSummeryList) {
    public record FolderSummery(
            Long folderId,
            String folderName,
            String folderColor,
            Long order
    ){

    }
}
