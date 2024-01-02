package com.teami.domain.friend.controller.dto.response;

import com.teami.domain.calendar.entitty.CalendarMission;
import lombok.Builder;

import java.util.List;

@Builder
public record FriendMissionInfo(
        Long memberId,
        Long friendMemberId,
        List<CalendarMission> missionContentList
) {
}
