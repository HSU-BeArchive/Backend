package com.likelion.moamoa.common.chat.web.controller;


import com.likelion.moamoa.common.chat.service.ChatService;
import com.likelion.moamoa.common.chat.web.dto.ChatMessageReq;
import com.likelion.moamoa.common.chat.web.dto.ChatMessageRes;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
@RequestMapping("/chat") // 이 컨트롤러의 모든 핸들러 메서드에 대한 기본 URL 경로를 설정합니다.
@RequiredArgsConstructor // Lombok 어노테이션으로, final 필드에 대한 생성자를 자동으로 생성하여 의존성 주입을 간소화합니다.
public class ChatController {

    private final ChatService chatService; // ChatService 인터페이스를 주입받습니다. 실제 구현체는 Spring이 찾아줍니다.

    /**
     * 사용자의 채팅 메시지를 받아 챗봇 응답을 생성하고 반환하는 API 엔드포인트.
     * HTTP POST 요청을 처리합니다.
     *
     * @param chatMessageReq 사용자가 보낸 메시지와 관련 정보를 담은 DTO (userId, recommendationId, message, sessionId)
     * @return 챗봇의 응답 메시지를 담은 SuccessResponse 객체와 HTTP 상태 코드 200 (OK)
     */
    @PostMapping // HTTP POST 요청을 처리하는 메서드를 나타냅니다. /chat 경로로 POST 요청이 오면 이 메서드가 실행됩니다.
    public ResponseEntity<SuccessResponse<?>> sendMessage(
            @RequestBody @Valid ChatMessageReq chatMessageReq // HTTP 요청 본문을 ChatMessageReq 객체로 매핑합니다. @Valid는 DTO의 유효성 검증을 수행합니다.
    ) {
        // ChatService의 sendMessage 메서드를 호출하여 챗봇 응답을 받습니다.
        ChatMessageRes chatMessageRes = chatService.sendMessage(chatMessageReq);

        // HTTP 응답을 생성하여 반환합니다.
        // HttpStatus.OK (200) 상태 코드와 함께 SuccessResponse에 챗봇 응답 데이터를 담아 보냅니다.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(chatMessageRes));
    }
}
