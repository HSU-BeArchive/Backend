package com.likelion.moamoa.domain.chat.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageReq {

    private Long userId;

    private Long recommendationId;

    private String message;

}
