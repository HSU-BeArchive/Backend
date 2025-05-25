package com.likelion.moamoa.domain.folder.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyFolderReq {

    @NotBlank(message = "변경할 폴더명은 필수값입니다.")
    private String folderName;
}
