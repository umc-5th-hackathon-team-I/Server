package com.teami.domain.friend.controller;

import com.teami.domain.friend.controller.dto.response.FriendCalendarInfo;
import com.teami.domain.friend.controller.dto.response.FriendListResponse;
import com.teami.domain.friend.service.FriendService;
import com.teami.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendService friendService;

    // 친구 추가
    @PostMapping("/create/{receiverId}/{senderId}")
    public ApiResponse<Void> crateFriend(@PathVariable Long receiverId, @PathVariable Long senderId) {
        friendService.createFriend(receiverId, senderId);
        return ApiResponse.onSuccess(null);
    }

    // 친구 삭제
    @DeleteMapping("/{requesterId}/{requestedId}")
    public ApiResponse<Void> deleteFriend(@PathVariable Long requesterId, @PathVariable Long requestedId) {
        friendService.deleteFriend(requesterId, requestedId);
        return ApiResponse.onSuccess(null);
    }

    // 친구 목록 조회
    @GetMapping("/list")
    public ApiResponse<FriendListResponse> getFriendList(@RequestParam Long memberId) {
        FriendListResponse response = friendService.getFriendList(memberId);
        return ApiResponse.onSuccess(response);
    }

    // 친구 캘린더 아이디 조회
    @GetMapping("/calendar/{friendMemberId}")
    public ApiResponse<FriendCalendarInfo> getFriendCalendarInfo(@RequestParam Long memberId, @PathVariable Long friendMemberId) {
        FriendCalendarInfo response = friendService.getCalendarInfo(memberId, friendMemberId);
        return ApiResponse.onSuccess(response);
    }
}