package com.teami.domain.member.dto.request;

import com.teami.domain.member.entitty.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequest {
    private String loginId;
    private String nickname;

    private String password;

}
