package com.likelion.moamoa.domain.reference.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateImgNameException extends BaseException {
    public DuplicateImgNameException() {
        super(ReferenceErrorCode.REFERENCE_DUPLICATE_NAME_409);
    }
}
