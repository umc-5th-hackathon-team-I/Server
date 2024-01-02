package com.teami.domain.reward.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.teami.domain.reward.entity.RewardType.*;

@Getter
@AllArgsConstructor
public enum Reward {
    SIGNUP_AND_CALENDAR_CREATE(0L, "회원가입 후 캘린더를 생성했어요!", MEMBER),
    COMPLETE_FIVE_MISSION(1L, "5개의 미션을 연속으로 완료했어요!", MISSION),
    COMPLETE_FIFTY_MISSION(2L, "50개의 미션을 연속으로 완료했어요!", MISSION),
    COMPLETE_ONE_CALENDAR(3L, "캘린더 한 판을 완성했어요!", CALENDAR),
    COMPLETE_FIVE_CALENDARS(4L, "캘린더 다섯 판을 완성했어요!", CALENDAR),
    CREATE_FIVE_FRIENDS(5L, "5명의 친구를 만들었어요!", FRIEND),
    WRITE_FIVE_LETTERS(6L, "5개의 편지를 썼어요!", FRIEND),
    WRITE_LETTERS_TO_THREE_FRIENDS_DAILY(7L, "하루에 3명의 친구에게 편지를 작성했어요!", FRIEND),
    CHECK_LETTERS_IN_MAILBOX(8L, "편지함에서 편지를 확인했어요!", FRIEND),
    EARN_FIVE_REWARDS(9L, "5개의 리워드를 모았어요!", REWARD)
    ;

    private Long rewardId;
    private final String content;
    private final RewardType type;
}
