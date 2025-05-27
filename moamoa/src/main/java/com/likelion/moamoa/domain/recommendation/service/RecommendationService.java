package com.likelion.moamoa.domain.recommendation.service;

import com.likelion.moamoa.domain.recommendation.web.dto.CreateRecommendationRes;
import com.likelion.moamoa.domain.recommendation.web.dto.RecommendationDetailRes;

public interface RecommendationService {
    // 추천 질문 생성
    CreateRecommendationRes createRecommendation(Long referenceId);

    // 추천 질문 조회
    RecommendationDetailRes getRecommendation(Long referenceId);
}
