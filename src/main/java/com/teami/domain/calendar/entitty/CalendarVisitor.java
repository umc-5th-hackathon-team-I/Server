package com.teami.domain.calendar.entitty;


import com.teami.domain.member.dto.request.VisitorCommentReq;
import com.teami.domain.member.entitty.Member;
import com.teami.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class CalendarVisitor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member writer;

    private String content;

    private boolean isChecked;

    public CalendarVisitor(VisitorCommentReq visitorCommentReq, Member owner, Member writer){
        this.owner = owner;
        this.writer = writer;
        this.content = visitorCommentReq.getContent();

    }
}
