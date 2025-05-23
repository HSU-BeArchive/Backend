package com.likelion.moamoa.domain.reference.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundReferenceException extends BaseException {
    public NotFoundReferenceException() {
        super(ReferenceErrorCode.REFERENCE_NOT_FOUND_404);
    }
}
