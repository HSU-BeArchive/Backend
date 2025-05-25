package com.likelion.moamoa.common.chat.service;

import com.likelion.moamoa.common.chat.web.dto.ChatMessageReq;
import com.likelion.moamoa.common.chat.web.dto.ChatMessageRes;

import java.util.List;

public interface ChatService {
    // 메시지 전송
    ChatMessageRes sendMessage(ChatMessageReq chatMessageReq);

    // 메서드 시그니처는 동일
    List<ChatMessageRes> getChatHistory(Long recommendation);

}
