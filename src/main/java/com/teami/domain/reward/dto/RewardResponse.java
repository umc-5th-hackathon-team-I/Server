package com.teami.domain.reward.dto;

import com.teami.domain.reward.entity.RewardList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RewardResponse {

    private RewardList rewardList;
}
