package com.likelion.moamoa.domain.recommendation.service;

public interface OpenAiService {
    String getRecommendationQuestion(String imgUrl,String description);
}
