package com.likelion.moamoa.common.chat.service;


import com.likelion.moamoa.common.chat.entitiy.Chat;
import com.likelion.moamoa.common.chat.entitiy.MessageRole;
import com.likelion.moamoa.common.chat.exception.NotFoundRecommendationException;
import com.likelion.moamoa.common.chat.repository.ChatRepository;
import com.likelion.moamoa.common.chat.web.dto.ChatMessageReq;
import com.likelion.moamoa.common.chat.web.dto.ChatMessageRes;
import com.likelion.moamoa.common.config.OpenAiConfig;
import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.common.question.repository.RecommendationRepository;
import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.auth.exception.NotFoundLoginIdException;
import com.likelion.moamoa.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;
    private final ChatRepository chatRepository;
    private final OpenAiConfig openAiConfig; // OpenAiConfig 주입
    private final RestTemplate restTemplate; // RestTemplate 주입

    // 메시지 전송 메서드
    @Override
    public ChatMessageRes sendMessage(ChatMessageReq chatMessageReq) {

        // 사용자 검증 (findById는 기본키를 검증)
        User user = userRepository.findById(chatMessageReq.getUserId())
                .orElseThrow(NotFoundLoginIdException::new);

        // 추천 질문 검증
        Recommendation recommendation = recommendationRepository.findById(chatMessageReq.getRecommendationId())
                .orElseThrow(NotFoundRecommendationException::new);


        // 사용자 메시지 저장
        Chat userLog = Chat.builder()
                .recommendation(recommendation)
                .content(chatMessageReq.getMessage())
                .messageRole(MessageRole.USER)
                .build();
        chatRepository.save(userLog);

        // 이전 대화 내용 가져오기(챗봇이 전의 대화를 이해할 수 있도록)
        List<Chat> previousLogs = chatRepository.findByRecommendationRecommendationIdOrderByCreatedAtAsc(chatMessageReq.getRecommendationId());

        // OpenAI API에 전송할 메시지 형식으로 변환
        // role과 content를 키-값 쌍으로 가짐
        List<Map<String, String>> messages = new ArrayList<>();

        // 시스템 메시지: 추천 질문을 기반으로 브레인스토밍을 돕도록 설정
        // system은 챗봇에게 특정 역할과 행동 지침을 부여함, 우리가 원하는 방식으로 응답하도록 프롬포팅 하는 것
        messages.add(Map.of("role", "system",
                "content", "당신은 사용자의 창의적인 브레인스토밍을 적극적으로 돕는 AI 챗봇입니다. " +
                        "사용자의 질문과 아이디어에 대해 단순히 정보를 제공하는 것을 넘어, " +
                        "다양한 관점을 제시하고, 새로운 아이디어를 제안하며, " +
                        "심층적인 질문을 통해 사용자의 생각을 확장하고 발전시키도록 유도하세요. " +
                        "핵심은 '질문을 통해 다음 아이디어를 이끌어내는 것'입니다. " +
                        "예를 들어, '이 아이디어의 장점은 무엇일까요?', '다른 방식으로 접근한다면?', " +
                        "'이 아이디어가 현실화될 때 예상되는 어려움은 무엇인가요?'와 같은 질문을 던지며 대화를 이어가세요. " +
                        "당신은 항상 대화의 흐름을 주도하며 사용자의 아이디어가 구체화되고 풍부해지도록 돕습니다. " +
                        "답변은 항상 완성된 문장으로 구성하며, 최대 4줄을 넘지 않도록 간결하게 작성해주세요. " + // 이 부분이 중요!
                        "현재 브레인스토밍 중인 추천 질문은 다음과 같습니다: " +
                        recommendation.getQuestion()));


        // 이전 대화 내용 추가 (최근 10개 메시지)
        int startIndex = Math.max(0, previousLogs.size() - 10); // previousLogs에 저장 되어 있는 채팅 중 최근 10개 메시지부터 시작
        for (int i = startIndex; i < previousLogs.size(); i++) {
            Chat log = previousLogs.get(i);
            // 발신자(AI, USER) 확인
            String role = log.getMessageRole() == MessageRole.USER ? "user" : "assistant";
            messages.add(Map.of("role", role, "content", log.getContent()));
        }

        // 현재 사용자 메시지 추가
        messages.add(Map.of("role", "user", "content", chatMessageReq.getMessage()));


        // AI 응답 생성
        String botResponse = getResponseFromOpenAI(messages);

        // 봇 응답 저장
        Chat botLog = Chat.builder()
                .recommendation(recommendation)
                .content(botResponse)
                .messageRole(MessageRole.AI)
                .build();
        Chat savedBotLog = chatRepository.save(botLog);

        // 응답 반환
        return ChatMessageRes.builder()
                .chatId(savedBotLog.getChatId())
                .recommendationId(recommendation.getRecommendationId())
                .message(savedBotLog.getContent())
                .messageRole(savedBotLog.getMessageRole())
                .createdAt(savedBotLog.getCreatedAt())
                .build();
    }

    // OpenAI API 호출 로직 구현
    private String getResponseFromOpenAI(List<Map<String, String>> messages) {
        // 1. 요청 본문(body) 만들기
        Map<String, Object> body = new HashMap<>();
        body.put("model", openAiConfig.getModel()); // 사용할 AI 모델 지정
        body.put("messages", messages); //챗봇과 주고 받은 대화 내용
        body.put("temperature", 0.7); // 창의성 조절 (0.0 - 2.0, 기본값 1.0)
        body.put("max_tokens", 200); // 최대 응답 길이 제한

        // 2. Http 헤더 준비
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // 요청 본문 json
        headers.setBearerAuth(openAiConfig.getApiKey()); // api 키 설정

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            // 3. GPT-4 API 호출
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    openAiConfig.getApiUrl() , // api 엔드포인트 url
                    request,
                    Map.class
            );
            // 응답 처리 시작(HTTP 응답의 바디 부분만 추출하여 Map로 변환)
            Map<String, Object> responseBody = response.getBody();
            // choices 배열 존재 확인
            // openAi API 응답 구조:{"choices": [{"message": {"content": "....."}}]}
            if (responseBody != null && responseBody.containsKey("choices")) {
                // choices 배열 추출 및 검증
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    // 첫 번째 선택지 추출
                    Map<String, Object> firstChoice = choices.get(0);
                    if (firstChoice.containsKey("message")) {
                        // 메시지 객체 추출
                        Map<String, String> message = (Map<String, String>) firstChoice.get("message");
                        if (message.containsKey("content")) {
                            // 실제 응답 내용 반환
                            return message.get("content").trim();
                        }
                    }
                }
            }
            throw new RuntimeException("GPT-4 API 응답에서 content를 찾을 수 없습니다.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("GPT-4 API 호출 실패: " + e.getMessage());
        }
    }
  
    @Override     
    public List<ChatMessageRes> getChatHistory(Long recommendation) {
        List<Chat> chatLogs = chatRepository.findByRecommendation_RecommendationIdOrderByCreatedAtAsc(recommendation);

        return chatLogs.stream()
                .map(chat -> ChatMessageRes.builder()
                        .chatId(chat.getChatId())
                        .recommendationId(chat.getRecommendation().getRecommendationId())
                        .message(chat.getContent())
                        .messageRole(chat.getMessageRole())
                        .createdAt(chat.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}