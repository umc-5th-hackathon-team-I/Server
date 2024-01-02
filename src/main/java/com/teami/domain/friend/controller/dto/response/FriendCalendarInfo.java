package com.teami.domain.friend.controller.dto.response;

public record FriendCalendarInfo(
        Long memberId,
        Long friendMemberId,
        Long calendarId
) {
}
