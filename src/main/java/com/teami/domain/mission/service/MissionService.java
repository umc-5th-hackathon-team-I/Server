package com.teami.domain.mission.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teami.domain.mission.dto.request.ChatGPTRequest;
import com.teami.domain.mission.dto.request.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    @Value("${chat.api}")
    private String API_KEY;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";


    public String generateMission(String prompt) throws Exception {
        List<ChatRequest> messages = new ArrayList<>();
        messages.add(new ChatRequest("user", prompt));
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest("gpt-4", messages);

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(chatGPTRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENAI_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
