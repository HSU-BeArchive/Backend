package com.likelion.moamoa.domain.folder.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FolderErrorCode implements BaseResponseCode {
    FOLDER_NOT_FOUND_USER_404("FOLDER_NOT_FOUND_USER_404",404,"해당 사용자를 찾을 수 없습니다."),
    FOLDER_NOT_FOUND_404("FOLDER_NOT_FOUND_404", 404, "해당 폴더를 찾을 수 없습니다."),
    FOLDER_DUPLICATE_NAME_409("FOLDER_DUPLICATE_409",409,"이미 존재하는 폴더명입니다.");

    private final String code;
    private final int httpStatus;
    private final String message;

}

