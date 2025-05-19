package com.likelion.moamoa.domain.folder.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateFolderReq {
    @NotBlank
    private String folderName;

    @NotBlank
    private String folderColor;
}
