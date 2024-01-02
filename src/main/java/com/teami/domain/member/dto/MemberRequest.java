package com.teami.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberRequest {
    private String loginId;
    private String nickname;
    private String password;
}
