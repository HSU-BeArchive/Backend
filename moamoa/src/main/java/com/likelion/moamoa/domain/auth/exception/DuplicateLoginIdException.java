package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateLoginIdException extends BaseException {
    public DuplicateLoginIdException() {
        super(AuthErrorCode.USER_DUPLICATE_LOGINID_409);
    }
}
