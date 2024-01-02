package com.teami.domain.reward.repository;

import com.teami.domain.member.entitty.Member;
import com.teami.domain.reward.dto.RewardResponse;
import com.teami.domain.reward.entity.MemberReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRewardRepository extends JpaRepository<MemberReward, Long> {
    List<RewardResponse> findByMemberId(Long MemberId);
    boolean existsByMemberAndRewardId(Member member, Long rewardId);
}
