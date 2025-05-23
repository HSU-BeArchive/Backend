package com.likelion.moamoa.common.chat.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageReq {

    private Long userId;

    private Long recommendationId;

    private String sessionId;

    private String message;

}
