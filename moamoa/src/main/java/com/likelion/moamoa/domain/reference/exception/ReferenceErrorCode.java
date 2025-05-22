package com.likelion.moamoa.domain.reference.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReferenceErrorCode implements BaseResponseCode {
    REFERENCE_DUPLICATE_NAME_409("REFERENCE_DUPLICATE_NAME_409", 409, "이미 존재하는 파일명입니다.");


    private final String code;
    private final int httpStatus;
    private final String message;

}
