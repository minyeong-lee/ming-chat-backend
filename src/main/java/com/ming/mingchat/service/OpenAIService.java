package com.ming.mingchat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    private final String apiKey;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public OpenAIService (@Value("${openai.api-key") String apiKey) {
        this.apiKey = apiKey;
    }

    public String getChatCompletions(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();

        // 메시지 포맷
        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content",
                        "당신은 감정 중재에 특화된 커플 심리상담사 AI입니다. " +
                                "두 사람의 갈등을 해결할 수 있도록 다음과 같은 단계를 거쳐 대화를 이끕니다: " +
                                "1. 목적 및 규칙 설명, 2. 감정 배제 상황 대화 유도, 3. 상황 요약 및 인식 차이 정리, " +
                                "4. 감정 표현 유도, 5. 대화 리포트 생성. " +
                                "상대방을 자극하는 언어나 주제 이탈, 과도한 감정 표현이 감지되면 부드럽게 중재해주세요."
                ),
                Map.of("role", "user", "content", userMessage)
        );

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

        return message.get("content").toString();
    }


}
