package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateLoginIdException extends BaseException {
    public DuplicateLoginIdException() {
        super(AuthErrorCode.SIGNUP_EXISTING_USERID_409);
    }
}
