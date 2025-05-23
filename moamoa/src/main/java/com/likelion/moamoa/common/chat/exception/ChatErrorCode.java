package com.likelion.moamoa.common.chat.exception;

import com.likelion.moamoa.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ChatErrorCode implements BaseResponseCode {
    RECOMMENDATION_NOT_FOUND_404("RECOMMENDATION_NOT_FOUND_404", 404, "추천질문이 없습니다."),
    //RECOMMENDATION_FORBIDDEN_403("RECOMMENDATION_FORBIDDEN_403", 403, "해당 추천 질문에 대한 접근 권한이 없습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
