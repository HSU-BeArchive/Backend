package com.likelion.moamoa.global.response.code.reference;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReferenceErrorCode implements BaseResponseCode {
    REFERENCE_DUPLICATE_NAME_409("REFERENCE_DUPLICATE_NAME_409", 409, "이미 존재하는 파일명입니다."),
    REFERENCE_NOT_FOUND_404("REFERENCE_NOT_FOUND_404", 404, "해당 래퍼런스를 찾을 수 없습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;

}
