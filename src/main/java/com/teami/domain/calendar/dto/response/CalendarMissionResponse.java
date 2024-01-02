package com.teami.domain.calendar.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalendarMissionResponse {
    private Long missionId;
    private String content;
    private boolean isComplete;
}
