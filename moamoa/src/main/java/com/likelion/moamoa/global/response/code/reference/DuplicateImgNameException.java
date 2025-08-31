package com.likelion.moamoa.global.response.code.reference;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateImgNameException extends BaseException {
    public DuplicateImgNameException() {
        super(ReferenceErrorCode.REFERENCE_DUPLICATE_NAME_409);
    }
}
