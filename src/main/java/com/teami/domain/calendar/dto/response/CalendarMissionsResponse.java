package com.teami.domain.calendar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CalendarMissionsResponse {
    private Long calenderId;
    private String nickname;
    private List<CalendarMissionResponse> missions;
}
