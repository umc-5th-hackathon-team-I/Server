package com.teami.domain.reward.repository;

import com.teami.domain.member.entitty.Member;
import com.teami.domain.reward.dto.RewardResponse;
import com.teami.domain.reward.entity.MemberReward;
import com.teami.domain.reward.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRewardRepository extends JpaRepository<MemberReward, Long> {
    List<RewardResponse> findByMemberId(Long MemberId);
    boolean existsByMemberAndReward(Member member, Reward reward);
    Long countMemberRewardByMember(Member member);
}
