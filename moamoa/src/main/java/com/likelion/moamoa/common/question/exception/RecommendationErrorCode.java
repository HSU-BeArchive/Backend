package com.likelion.moamoa.common.question.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecommendationErrorCode implements BaseResponseCode {
    REFERENCE_NOT_FOUND_404("REFERENCE_NOT_FOUND_404", 404, "레퍼런스가 존재하지 않습니다."),
    RECOMMENDATION_DUPLICATE_409("RECOMMENDATION_DUPLICATE_409", 409, "이미 추천질문이 존재합니다.");

    private final String code;
    private final int httpStatus;
    private final String message;

}
