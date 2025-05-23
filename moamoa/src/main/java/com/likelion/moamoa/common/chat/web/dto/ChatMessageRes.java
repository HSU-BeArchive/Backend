package com.likelion.moamoa.common.chat.web.dto;

import com.likelion.moamoa.common.chat.entitiy.MessageRole;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ChatMessageRes(
        Long chatId,
//        String sessionId,
        Long recommendationId,
        String message,
        MessageRole messageRole,
        LocalDateTime createdAt
) {
}
