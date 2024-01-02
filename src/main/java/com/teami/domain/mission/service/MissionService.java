package com.teami.domain.mission.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teami.domain.mission.dto.request.ChatGPTRequest;
import com.teami.domain.mission.dto.request.ChatRequest;
import com.teami.domain.mission.dto.response.NewMissionResponse;
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

    private static final String comment = "30일동안 하루 단위로 수행할 간단한 미션 생성해서 [n일차 : 미션] json 형태로 보내줘";

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public List<NewMissionResponse> generateMission() throws Exception {
        List<ChatRequest> messages = new ArrayList<>();
        messages.add(new ChatRequest("user", comment));
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

        // 응답 본문을 JSON으로 파싱
        JsonNode responseJson = mapper.readTree(response.body());

        // 'choices' 배열에서 첫 번째 항목의 'message' 객체를 가져옴
        JsonNode message = responseJson.get("choices").get(0).get("message");

        // 'message' 객체에서 'content' 필드를 가져옴
        String content = message.get("content").asText();

        // content에서 일자와 미션을 추출하여 Mission 객체로 만들고 리스트에 추가
        List<NewMissionResponse> missions = parseMissionFromContent(content);

        return missions;
    }

    public List<NewMissionResponse> parseMissionFromContent(String content) {
        System.out.println("준석" + content);
        List<NewMissionResponse> missions = new ArrayList<>();
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.contains(":")) {
                String[] parts = line.split(":");
                String mission = parts[1].trim();
                missions.add(new NewMissionResponse(mission));
            }
        }
        return missions;
    }
}
