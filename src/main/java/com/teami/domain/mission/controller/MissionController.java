package com.teami.domain.mission.controller;

import com.teami.domain.mission.dto.request.ChatGPTRequest;
import com.teami.domain.mission.service.MissionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("")
    public String chat(@RequestBody ChatMessage message) {
        String response;
        try {
            response = missionService.generateMission(message.getContent());
        } catch (Exception e) {
            response = "Error while communicating with OpenAI: " + e.getMessage();
        }
        return response;
    }
}

@Getter
class ChatMessage {
    private String content;

    // getter, setter
}
