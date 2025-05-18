package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseResponseCode {
    // 상태 코드가 제일 끝으로 가게!
    SIGNUP_EXISTING_USERID_409("SIGNUP_EXISTING_USERSID_404",409,"이미 존재하는 ID입니다."),
    SIGNIN_NOT_FOUND_404("SIGNIN_NOT_FOUND_404", 404, "해당 아이디가 존재하지 않습니다"),
    SIGNIN_PASSWORD_INVALID_403("POST_PASSWORD_INVALID_403", 403,"비밀번호가 일치하지 않습니다." );

    private final String code;
    private final int httpStatus;
    private final String message;
}
