package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseResponseCode {
    // 상태 코드가 제일 끝으로 가게!
    USER_DUPLICATE_LOGINID_409("USER_DUPLICATE_LOGINID_409",409,"이미 존재하는 ID입니다."),
    USER_NOT_FOUND_LOGINID_404("USER_NOT_FOUND_LOGINID_404", 404, "해당 아이디가 존재하지 않습니다."),
    USER_INVALID_PASSWORD_401("USER_INVALID_PASSWORD_401", 401,"비밀번호가 일치하지 않습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
