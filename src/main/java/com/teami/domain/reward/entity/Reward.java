package com.teami.domain.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.teami.domain.reward.entity.RewardType.*;

@Getter
@AllArgsConstructor
public enum Reward {
    SIGNUP_AND_CALENDAR_CREATE(0L, "회원가입 후 캘린더를 생성했어요!", MEMBER),
    COMPLETE_FIVE_MISSION(1L, "5개의 미션을 연속으로 완료했어요!", MISSION),
    COMPLETE_FIRST_MISSION(2L, "첫 미션을 완료했어요!", MISSION),
    RECEIVE_TEN_LETTERS(3L, "10개의 편지를 받았어요!", FRIEND),
    CHECK_LETTERS_IN_MAILBOX(4L, "편지함에서 편지를 처음으로 확인했어요!", FRIEND),
    EARN_FIVE_REWARDS(5L, "5개의 리워드를 모았어요!", REWARD)
    ;

    private Long rewardId;
    private final String content;
    private final RewardType type;
}
