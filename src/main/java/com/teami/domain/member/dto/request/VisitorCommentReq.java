package com.teami.domain.member.dto.request;

import lombok.Getter;

@Getter
public class VisitorCommentReq {
    private Long ownerId;
    private Long writerId;

    private String content;

}
