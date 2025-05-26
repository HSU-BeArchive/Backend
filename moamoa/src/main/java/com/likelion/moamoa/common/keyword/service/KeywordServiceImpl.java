package com.likelion.moamoa.common.keyword.service;


import com.likelion.moamoa.common.chat.entitiy.Chat;
import com.likelion.moamoa.common.chat.exception.NotFoundRecommendationException;
import com.likelion.moamoa.common.chat.repository.ChatRepository;
import com.likelion.moamoa.common.config.OpenAiConfig;
import com.likelion.moamoa.common.keyword.entity.Keyword;
import com.likelion.moamoa.common.keyword.repository.KeywordRepository;
import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordReq;
import com.likelion.moamoa.common.keyword.web.dto.ExtractKeywordRes;
import com.likelion.moamoa.common.question.entity.Recommendation;
import com.likelion.moamoa.common.question.repository.RecommendationRepository;
import com.likelion.moamoa.common.question.service.OpenAiService;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.folder.repository.FolderRepository;
import com.likelion.moamoa.domain.folder.exception.NotFoundFolderException;
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
public class KeywordServiceImpl implements KeywordService {

    private final FolderRepository folderRepository;
    private final KeywordRepository keywordRepository;
    private final RecommendationRepository recommendationRepository;
    private final ChatRepository chatRepository;
    private final OpenAiService openAiService;
    private final RestTemplate restTemplate;
    private final OpenAiConfig openAiConfig;

@Override
public List<ExtractKeywordRes> extractKeyword(ExtractKeywordReq extractKeywordReq) {
    // 폴더 확인
    Folder folder = folderRepository.findById(extractKeywordReq.getFolderId())
            .orElseThrow(NotFoundFolderException::new);

    // 추천질문 가져오기
    List<Recommendation> recommendations = recommendationRepository
            .findAllByFolder_FolderId(extractKeywordReq.getFolderId());

    if (recommendations.isEmpty()) {
        throw new NotFoundRecommendationException();
    }

    // 모든 채팅 내용 수집
    StringBuilder allChatContent = new StringBuilder();
    for (Recommendation recommendation : recommendations) {
        List<Chat> chatLogs = chatRepository
                .findByRecommendation_RecommendationIdOrderByCreatedAtAsc(recommendation.getRecommendationId());

        for (Chat chat : chatLogs) {
            allChatContent.append(chat.getContent()).append(" ");
        }
    }

    // 키워드 추출
    Map<String, Long> keywordCounts = extractKeywordsFromGPT(allChatContent.toString());

    List<Keyword> savedKeywords = new ArrayList<>();
    for (Map.Entry<String, Long> entry : keywordCounts.entrySet()) {
        Keyword keyword = Keyword.builder()
                .keywordName(entry.getKey())
                .keywordCount(entry.getValue())
                .folder(folder)
                .build();
        savedKeywords.add(keywordRepository.save(keyword));
    }

    return savedKeywords.stream()
            .map(keyword -> new ExtractKeywordRes(
                    folder.getFolderId(),
                    keyword.getKeywordId(),
                    keyword.getKeywordName(),
                    keyword.getKeywordCount()
            ))
            .toList();
}

    @Override
    public List<ExtractKeywordRes> getKeywords(Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        List<Keyword> keywords = keywordRepository.findByFolder_FolderId(folderId);

        return keywords.stream()
                .map(keyword -> new ExtractKeywordRes(
                        folder.getFolderId(),
                        keyword.getKeywordId(),
                        keyword.getKeywordName(),
                        keyword.getKeywordCount()
                ))
                .toList();
    }

    private Map<String, Long> extractKeywordsFromGPT(String chatContent) {
    List<Map<String, String>> messages = new ArrayList<>();
    messages.add(Map.of(
        "role", "user",
        "content", "다음 텍스트에서 중요한 키워드 5개를 뽑고 각각 몇 번 나왔는지 세어서 " +
                  "'키워드:횟수,키워드:횟수' 형식으로만 답해줘. 예: 인공지능:3,데이터:2\n\n" + chatContent
    ));

    Map<String, Object> body = new HashMap<>();
    body.put("model", openAiConfig.getModel());
    body.put("messages", messages);
    body.put("max_tokens", 200);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(openAiConfig.getApiKey());

    try {
        // API 호출
        ResponseEntity<Map> response = restTemplate.postForEntity(
                openAiConfig.getApiUrl(),
                new HttpEntity<>(body, headers),
                Map.class
        );

        // 응답 처리
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            if (!choices.isEmpty()) {
                Map<String, Object> firstChoice = choices.get(0);
                if (firstChoice.containsKey("message")) {
                    Map<String, String> message = (Map<String, String>) firstChoice.get("message");
                    if (message.containsKey("content")) {
                        String gptAnswer = message.get("content").trim();
                        return parseKeywordResponse(gptAnswer);
                    }
                }
            }
        }
    } catch (Exception e) {
        throw new RuntimeException("키워드 추출 중 오류가 발생했습니다.", e);
    }

    // 실패 시 기본값 반환
    Map<String, Long> defaultResult = new HashMap<>();
    defaultResult.put("키워드추출실패", 1L);
    return defaultResult;
}

private Map<String, Long> parseKeywordResponse(String gptAnswer) {
    Map<String, Long> result = new HashMap<>();
    String[] pairs = gptAnswer.split(",");

    for (String pair : pairs) {
        if (pair.contains(":")) {
            String[] parts = pair.trim().split(":");
            if (parts.length == 2) {
                try {
                    result.put(parts[0].trim(), Long.parseLong(parts[1].trim()));
                } catch (NumberFormatException e) {
                    // 파싱 실패 시 로깅
                    System.err.println("실패: " + pair);
                }
            }
        }
    }
    return result;
}}
