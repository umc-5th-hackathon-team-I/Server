package com.teami.domain.calendar.mapper;

import com.teami.domain.calendar.dto.response.CalendarMissionResponse;
import com.teami.domain.calendar.dto.response.CalendarMissionsResponse;
import com.teami.domain.calendar.entitty.Calendar;
import com.teami.domain.calendar.entitty.CalendarMission;
import com.teami.domain.member.entitty.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component

public class CalendarMapper {
    public Calendar toEntity(Member member) {
        return Calendar.builder()
                .member(member)
                .isComplete(false)
                .build();
    }

    public CalendarMission toCalendarMission(String content, LocalDate date, boolean isComplete, Calendar calendar) {
        return CalendarMission.builder()
                .content(content)
                .date(date)
                .isComplete(isComplete)
                .calendar(calendar)
                .build();
    }

    public CalendarMissionsResponse toCalendarMissionsResponse(Member member, Calendar calendar, List<CalendarMissionResponse> missionResponses) {
        return new CalendarMissionsResponse(calendar.getId(), member.getNickname(),  missionResponses);
    }

    public List<CalendarMissionResponse> toCalendarMissionResponseList(List<CalendarMission> missions) {
        return missions.stream()
                .map(this::toCalendarMissionResponse)
                .toList();
    }

    public CalendarMissionResponse toCalendarMissionResponse(CalendarMission mission) {
        return new CalendarMissionResponse(
                mission.getId(), mission.getContent(), mission.getDate(), mission.isComplete()
        );
    }

}
