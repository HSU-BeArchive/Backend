package com.likelion.moamoa.domain.auth.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundLoginIdException extends BaseException {
    public NotFoundLoginIdException() {
        super(AuthErrorCode.USER_NOT_FOUND_LOGINID_404);
    }
}
