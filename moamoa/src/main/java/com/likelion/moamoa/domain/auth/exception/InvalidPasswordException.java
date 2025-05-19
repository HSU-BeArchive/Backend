package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException() {
        super(AuthErrorCode.USER_INVALID_PASSWORD_401);
    }
}
