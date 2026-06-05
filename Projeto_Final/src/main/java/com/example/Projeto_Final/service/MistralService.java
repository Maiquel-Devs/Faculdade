package com.example.Projeto_Final.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class MistralService {

    @Value("${mistral.token}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String pedirParaIA(String comando) {
        String url = "https://api.mistral.ai/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "mistral-tiny");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", comando);

        requestBody.put("messages", Collections.singletonList(message));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            List choices = (List) response.getBody().get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map messageObj = (Map) firstChoice.get("message");
            return (String) messageObj.get("content");
        } catch (Exception e) {
            return "Não foi possível gerar os dados: " + e.getMessage();
        }
    }
}