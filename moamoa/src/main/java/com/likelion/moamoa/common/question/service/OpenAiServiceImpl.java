package com.likelion.moamoa.common.question.service;

import com.likelion.moamoa.common.question.config.OpenAiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements OpenAiService {
    private final OpenAiConfig openAiConfig;
    private final RestTemplate restTemplate;

    @Override
    public String getRecommendationQuestion(String imgUrl, String description) {
        String prompt = String.format(
                "이미지 URL: %s\n설명: %s\n위 내용을 참고해 이 이미지를 바탕으로 브레인스토밍을 시작할 수 있는 추천 질문을 한 문장으로 만들어줘.",
                imgUrl, description
        );

        Map<String, Object> body = new HashMap<>();
        body.put("model", openAiConfig.getModel());
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "당신은 이미지를 분석해 브레인스토밍 질문을 만드는 AI입니다."));
        messages.add(Map.of("role", "user", "content", prompt));
        body.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiConfig.getApiKey());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    openAiConfig.getApiUrl(), request, Map.class
            );
            List<Map> choices = (List<Map>) response.getBody().get("choices");
            Map message = (Map) ((Map) choices.get(0)).get("message");
            String result = (String) message.get("content");
            return result.trim();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("GPT-4o API 호출 실패");
        }
    }
}
