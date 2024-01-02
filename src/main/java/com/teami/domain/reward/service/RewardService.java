package com.teami.domain.reward.service;

import com.teami.domain.member.entitty.Member;
import com.teami.domain.reward.dto.RewardResponse;
import com.teami.domain.reward.entity.MemberReward;
import com.teami.domain.reward.entity.Reward;
import com.teami.domain.reward.repository.MemberRewardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private final MemberRewardRepository memberRewardRepository;

    public RewardService(MemberRewardRepository memberRewardRepository) {
        this.memberRewardRepository = memberRewardRepository;
    }

    // 리워드 획득
    public void getRewardForMember(Member member, Reward reward) {
        MemberReward memberReward = MemberReward.builder()
                .member(member)
                .reward(reward)
                .build();
        memberRewardRepository.save(memberReward);
    }

    // 얻은 리워드 리스트로 조회
    public List<RewardResponse> getMyRewardList(Long memberId) {
        return memberRewardRepository.findByMemberId(memberId);
    }
}