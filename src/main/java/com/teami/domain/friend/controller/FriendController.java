package com.teami.domain.friend.controller;

import com.teami.domain.friend.controller.dto.response.FriendCalendarInfo;
import com.teami.domain.friend.controller.dto.response.FriendListResponse;
import com.teami.domain.friend.service.FriendService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendService friendService;

    // 친구 추가
    @Operation(summary = "친구 추가 API",description = "친구를 추가하는 API이다")
    @PostMapping("/create/{receiverId}/{senderId}")
    @Parameters({
            @Parameter(name = "receiverId", description = "친구 신청 받는 id이다"),
            @Parameter(name = "senderId", description = "친구 신청을 보내는 id이다")

    })
    public ApiResponse<Void> crateFriend(@PathVariable Long receiverId, @PathVariable Long senderId) {
        friendService.createFriend(receiverId, senderId);
        return ApiResponse.onSuccess(null);
    }

    // 친구 삭제
    @Operation(summary = "친구 삭제 API",description = "친구를 삭제하는 API이다")
    @DeleteMapping("/{requesterId}/{requestedId}")
    @Parameters({
            @Parameter(name = "requesterId", description = "친구 신청 하는 사람 id이다"),
            @Parameter(name = "requestedId", description = "친구 신청 받는 사람 id이다")

    })
    
    public ApiResponse<Void> deleteFriend(@PathVariable Long requesterId, @PathVariable Long requestedId) {
        friendService.deleteFriend(requesterId, requestedId);
        return ApiResponse.onSuccess(null);
    }

    // 친구 목록 조회
    @Operation(summary = "친구 목록 조회 API",description = "친구 목록 조회하는 API이다")
    @GetMapping("/list")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 id"),

    })
    public ApiResponse<FriendListResponse> getFriendList(@RequestParam Long memberId) {
        FriendListResponse response = friendService.getFriendList(memberId);
        return ApiResponse.onSuccess(response);
    }

    // 친구 캘린더 아이디 조회
    @Operation(summary = " 친구 캘린더 아이디 조회 API",description = " 친구 캘린더 아이디 조회하는 API이다")
    @GetMapping("/calendar/{friendMemberId}")
    @Parameters({
            @Parameter(name = "friendMemberId", description = "친구 id"),

    })
    public ApiResponse<FriendCalendarInfo> getFriendCalendarInfo(@RequestParam Long memberId, @PathVariable Long friendMemberId) {
        FriendCalendarInfo response = friendService.getCalendarInfo(memberId, friendMemberId);
        return ApiResponse.onSuccess(response);
    }
}