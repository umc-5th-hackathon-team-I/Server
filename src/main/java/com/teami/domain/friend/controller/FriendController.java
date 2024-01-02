package com.teami.domain.friend.controller;

import com.teami.domain.friend.service.FriendService;
import com.teami.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}