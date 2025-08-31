package com.likelion.moamoa.global.response.code.user;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundEmailException extends BaseException {
    public NotFoundEmailException() {
        super(UserErrorCode.USER_NOT_FOUND_EMAIL_404);
    }
}
