package com.likelion.moamoa.domain.question.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateRecommendationException extends BaseException {
  public DuplicateRecommendationException() {
    super(RecommendationErrorCode.RECOMMENDATION_DUPLICATE_409);
  }
}
