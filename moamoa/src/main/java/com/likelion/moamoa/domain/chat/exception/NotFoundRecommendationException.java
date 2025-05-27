package com.likelion.moamoa.domain.chat.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundRecommendationException extends BaseException {
    public NotFoundRecommendationException() {
        super(ChatErrorCode.RECOMMENDATION_NOT_FOUND_404);
    }
}
