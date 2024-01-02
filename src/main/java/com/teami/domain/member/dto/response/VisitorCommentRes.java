package com.teami.domain.member.dto.response;

import com.teami.domain.calendar.entitty.CalendarVisitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VisitorCommentRes {
    private Long id;
    private Long ownerId;

    private Long writerId;

    private String writerNickname;

    private String content;

    private boolean isChecked;

    public VisitorCommentRes(CalendarVisitor c){
        this.id = c.getId();
        this.ownerId = c.getOwner().getId();
        this.writerId = c.getWriter().getId();
        this.content = c.getContent();
        this.writerNickname = c.getWriter().getNickname();
        this.isChecked = c.isChecked();

    }
    public VisitorCommentRes(CalendarVisitor c, boolean isChecked){
        this.id = c.getId();
        this.ownerId = c.getOwner().getId();
        this.writerId = c.getWriter().getId();
        this.content = c.getContent();
        this.writerNickname = c.getWriter().getNickname();
        this.isChecked = isChecked;

    }
}
