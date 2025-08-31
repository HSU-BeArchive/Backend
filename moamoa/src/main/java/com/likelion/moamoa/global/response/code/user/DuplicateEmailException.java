package com.likelion.moamoa.global.response.code.user;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateEmailException extends BaseException {
    public DuplicateEmailException() {
        super(UserErrorCode.USER_DUPLICATE_EMAIL_409);
    }
}
