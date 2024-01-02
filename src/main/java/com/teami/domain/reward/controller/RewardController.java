package com.teami.domain.reward.controller;

import com.teami.domain.reward.dto.RewardResponse;
import com.teami.domain.reward.entity.MemberReward;
import com.teami.domain.reward.service.RewardService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/rewards")
@RestController
public class RewardController {

    private final RewardService rewardService;
    @GetMapping
    @Operation(summary = "리워드 조회 API")
    public ApiResponse<List<RewardResponse>> getMyReward(@RequestParam Long memberId) {
        return ApiResponse.onSuccess(rewardService.getMyRewardList(memberId));
    }
}
