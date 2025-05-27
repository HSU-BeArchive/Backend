package com.likelion.moamoa.domain.recommendation.web.dto;

public record RecommendationDetailRes(
        Long recommendationId,
        String question,
        Long userId,
        Long folderId,
        Long referenceId
) {
}
