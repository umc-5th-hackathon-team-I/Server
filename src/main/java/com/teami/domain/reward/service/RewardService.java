package com.teami.domain.reward.service;

import com.teami.domain.member.entitty.Member;
import com.teami.domain.reward.dto.RewardResponse;
import com.teami.domain.reward.entity.MemberReward;
import com.teami.domain.reward.entity.Reward;
import com.teami.domain.reward.repository.MemberRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.teami.domain.reward.entity.Reward.COMPLETE_FIVE_MISSION;
import static com.teami.domain.reward.entity.Reward.SIGNUP_AND_CALENDAR_CREATE;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final MemberRewardRepository memberRewardRepository;

    public Reward createReward_Member(Member member) {
        if (!memberRewardRepository.existsByMemberAndReward(member, SIGNUP_AND_CALENDAR_CREATE)) {
            memberRewardRepository.save(MemberReward.createMemberReward(member, SIGNUP_AND_CALENDAR_CREATE));
            return SIGNUP_AND_CALENDAR_CREATE;
        }
        return null;
    }

    public Reward createReward_Mission(Member member) {
        //    COMPLETE_FIVE_MISSION(1L, "5개의 미션을 연속으로 완료했어요!", MISSION),
        if (!memberRewardRepository.existsByMemberAndReward(member, COMPLETE_FIVE_MISSION)) {
            // pass
        }
        return null;
    }

    public List<RewardResponse> getMyRewardList (Long memberId) {
        return memberRewardRepository.findByMemberId(memberId);
    }
}