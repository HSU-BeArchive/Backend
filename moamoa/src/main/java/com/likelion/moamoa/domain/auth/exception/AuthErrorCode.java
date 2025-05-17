package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseResponseCode {
    SIGNUP_409_EXISTING_USERID("SIGNUP_409_EXISTING_USERSID",409,"이미 존재하는 ID입니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
