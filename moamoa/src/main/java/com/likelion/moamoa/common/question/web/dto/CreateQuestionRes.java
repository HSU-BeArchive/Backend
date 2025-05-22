package com.likelion.moamoa.common.question.web.dto;

import lombok.Builder;

@Builder
public record CreateQuestionRes(
        Long recommendationId,
        Long userId,
        Long folderId,
        Long referenceId,
        String question
) {
}
