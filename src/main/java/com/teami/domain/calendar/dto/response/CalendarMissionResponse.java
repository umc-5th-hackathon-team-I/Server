package com.teami.domain.calendar.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CalendarMissionResponse {
    private Long missionId;
    private String content;
    private LocalDate date;
    private boolean isComplete;
}
