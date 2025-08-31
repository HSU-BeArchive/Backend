package com.likelion.moamoa.global.response.code.user;

import com.likelion.moamoa.global.exception.BaseException;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException() {
        super(UserErrorCode.USER_INVALID_PASSWORD_401);
    }
}
