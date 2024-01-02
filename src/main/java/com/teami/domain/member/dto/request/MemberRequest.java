package com.teami.domain.member.dto.request;

import lombok.Getter;

@Getter
public class MemberRequest {
    private String loginId;
    private String nickname;
    private String password;
}
