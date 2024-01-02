package com.teami.domain.friend.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FriendListResponse(
        Long memberId,
        List<Long> friendMemberIdList
) {
}
