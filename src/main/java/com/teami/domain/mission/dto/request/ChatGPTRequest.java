package com.teami.domain.mission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {
    private String model;
    private List<ChatRequest> messages;
}
