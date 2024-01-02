package com.teami.domain.reward.entity;

public enum RewardList {

    SIGNUP_AND_CALENDAR_CREATE("회원가입 후 캘린더를 생성했어요!", "회원"),
    COMPLETE_FIVE_MISSION("5개의 미션을 연속으로 완료했어요!", "미션"),
    COMPLETE_FIFTY_MISSION("50개의 미션을 연속으로 완료했어요!", "미션"),
    COMPLETE_ONE_CALENDAR("캘린더 한 판을 완성했어요!", "캘린더"),
    COMPLETE_FIVE_CALENDARS("캘린더 다섯 판을 완성했어요!", "캘린더"),
    CREATE_FIVE_FRIENDS("5명의 친구를 만들었어요!", "친구"),
    WRITE_FIVE_LETTERS("5개의 편지를 썼어요!", "친구"),
    WRITE_LETTERS_TO_THREE_FRIENDS_DAILY("하루에 3명의 친구에게 편지를 작성했어요!", "친구"),
    CHECK_LETTERS_IN_MAILBOX("편지함에서 편지를 확인했어요!", "친구"),
    EARN_FIVE_REWARDS("5개의 리워드를 모았어요!", "리워드");

    private final String content;
    private final String type;

    RewardList(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}
