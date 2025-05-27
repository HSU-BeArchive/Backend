package com.likelion.moamoa.domain.chat.web.dto;

import com.likelion.moamoa.domain.chat.entitiy.MessageRole;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ChatMessageRes(
        Long chatId,
        Long recommendationId,
        String message,
        MessageRole messageRole,
        LocalDateTime createdAt
) {
}
