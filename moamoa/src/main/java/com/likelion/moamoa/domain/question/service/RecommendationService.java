package com.likelion.moamoa.domain.question.service;

import com.likelion.moamoa.domain.question.web.dto.CreateQuestionRes;

public interface RecommendationService {
    // 추천 질문 생성
    CreateQuestionRes createRecommendation(Long referenceId);

    // 추천 질문 조회
    CreateQuestionRes getRecommendation(Long referenceId);
}
