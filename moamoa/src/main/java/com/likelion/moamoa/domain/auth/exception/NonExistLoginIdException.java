package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class NonExistLoginIdException extends BaseException {
    public NonExistLoginIdException() {
        super(AuthErrorCode.SIGNIN_NOT_FOUND_404);
    }
}
