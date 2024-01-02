package com.teami.domain.friend.controller.dto.response;

import com.teami.domain.calendar.controller.dto.response.CalendarInfo;
import lombok.Builder;

import java.util.List;

@Builder
public record FriendInfo(
        Long id,
        String nickname,
        List<CalendarInfo> calendarInfoList
) {
}
