package com.likelion.moamoa.domain.recommendation.web.dto;

import lombok.Builder;

@Builder
public record CreateRecommendationRes(
        Long recommendationId,
        String question,
        Long userId,
        Long folderId,
        Long referenceId
) {
}
