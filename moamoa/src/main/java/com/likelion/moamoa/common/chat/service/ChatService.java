package com.likelion.moamoa.common.chat.service;

import com.likelion.moamoa.common.chat.web.dto.ChatMessageReq;
import com.likelion.moamoa.common.chat.web.dto.ChatMessageRes;

public interface ChatService {
    // 메시지 전송
    ChatMessageRes sendMessage(ChatMessageReq chatMessageReq);

}
