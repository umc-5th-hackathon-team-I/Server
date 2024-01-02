package com.teami.domain.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RewardType {
    MEMBER("멤버"),
    MISSION("미션"),
    CALENDAR("캘린더"),
    FRIEND("친구"),
    REWARD("보상")
    ;

    private final String value;
}
