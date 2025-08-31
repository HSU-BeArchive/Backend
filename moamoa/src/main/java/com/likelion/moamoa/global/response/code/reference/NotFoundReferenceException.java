package com.likelion.moamoa.global.response.code.reference;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundReferenceException extends BaseException {
    public NotFoundReferenceException() {
        super(ReferenceErrorCode.REFERENCE_NOT_FOUND_404);
    }
}
