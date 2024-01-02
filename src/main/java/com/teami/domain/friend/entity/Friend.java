package com.teami.domain.friend.entity;

import com.teami.domain.member.entitty.Member;
import com.teami.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Table(name = "friend")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class Friend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member1_id")
    private Member member1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member2_id")
    private Member member2;

    @Builder
    public Friend(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }

    public static Friend createFriend(Member member1, Member member2) {
        return new Friend(member1, member2);
    }
}
