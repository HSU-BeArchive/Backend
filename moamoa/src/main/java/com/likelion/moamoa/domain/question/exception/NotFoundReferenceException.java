package com.likelion.moamoa.domain.question.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundReferenceException extends BaseException {
    public NotFoundReferenceException() {
        super(RecommendationErrorCode.REFERENCE_NOT_FOUND_404);
    }
}
