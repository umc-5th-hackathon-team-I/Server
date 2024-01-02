package com.teami.domain.reward.entity;

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
public class MemberReward extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Reward reward;

    @Builder
    public MemberReward(Member member, Reward reward) {
        this.member = member;
        this.reward = reward;
    }

    public static MemberReward createMemberReward(Member member, Reward reward) {
        return new MemberReward(member, reward);
    }
}
