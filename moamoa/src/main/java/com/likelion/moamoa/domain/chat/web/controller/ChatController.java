package com.likelion.moamoa.domain.chat.web.controller;


import com.likelion.moamoa.domain.chat.service.ChatService;
import com.likelion.moamoa.domain.chat.web.dto.ChatMessageReq;
import com.likelion.moamoa.domain.chat.web.dto.ChatMessageRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @PostMapping
    public ResponseEntity<SuccessResponse<?>> sendMessage(
            @RequestBody @Valid ChatMessageReq chatMessageReq
    ) {
        ChatMessageRes chatMessageRes = chatService.sendMessage(chatMessageReq);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(chatMessageRes));
    }


    @GetMapping("/history/{recommendationId}")
    public ResponseEntity<SuccessResponse<List<ChatMessageRes>>> getChatHistory(
            @PathVariable("recommendationId") Long recommendationId
    ) {
        List<ChatMessageRes> chatHistory = chatService.getChatHistory(recommendationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(chatHistory));
    }
}
