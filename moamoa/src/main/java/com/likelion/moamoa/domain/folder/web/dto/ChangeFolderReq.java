package com.likelion.moamoa.domain.folder.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeFolderReq {

    @NotNull(message = "폴더의 변경될 위치는 필수값입니다.")
    Long folderOrderAfter;
}
