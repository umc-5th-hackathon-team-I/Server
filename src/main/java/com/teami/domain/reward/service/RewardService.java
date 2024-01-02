package com.teami.domain.reward.service;

import com.teami.domain.calendar.entitty.CalendarMission;
import com.teami.domain.calendar.repository.CalendarMissionRepository;
import com.teami.domain.calendar.repository.CalendarRepository;
import com.teami.domain.calendar.service.CalendarService;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.reward.dto.RewardResponse;
import com.teami.domain.reward.entity.MemberReward;
import com.teami.domain.reward.entity.Reward;
import com.teami.domain.reward.repository.MemberRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.teami.domain.reward.entity.Reward.*;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final MemberRewardRepository memberRewardRepository;
    private final CalendarRepository calendarRepository;
    private final CalendarMissionRepository calendarMissionRepository;

    public Reward createReward_Member(Member member) {
        createReward_Reward(member);

        if (!memberRewardRepository.existsByMemberAndReward(member, SIGNUP_AND_CALENDAR_CREATE)) {
            memberRewardRepository.save(MemberReward.createMemberReward(member, SIGNUP_AND_CALENDAR_CREATE));
            return SIGNUP_AND_CALENDAR_CREATE;
        }
        return null;
    }

    public List<CalendarMission> find5CalendarMissions(Long calendarId, LocalDate now) {
        List<CalendarMission> calendarMissionList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            now = now.minusDays(1);
            calendarMissionList.add(calendarMissionRepository.findCalendarMissionByCalendarIdAndDate(calendarId, now));
        }

        return calendarMissionList;
    }
    public Reward createReward_Mission1(Member member) {
        createReward_Reward(member);

        if (!memberRewardRepository.existsByMemberAndReward(member, COMPLETE_FIVE_MISSION)) {
            Long calendarId = calendarRepository.findByMemberAndIsComplete(member, false).get().getId();
            List<CalendarMission> calendarMissions = find5CalendarMissions(calendarId, LocalDate.now());

            for (int i = 0; i < 5; i++) {
                if (calendarMissions.get(i).isComplete() == false) {
                    return null;
                }
            }

            memberRewardRepository.save(MemberReward.createMemberReward(member, COMPLETE_FIVE_MISSION));
            return COMPLETE_FIVE_MISSION;
        }
        return null;
    }

    public Reward createReward_Mission2(Member member) {
        createReward_Reward(member);

        if (!memberRewardRepository.existsByMemberAndReward(member, COMPLETE_FIRST_MISSION)) {
            List<CalendarMission> calendarMissionList = calendarMissionRepository.findAll();
            for (CalendarMission c: calendarMissionList) {
                if (c.isComplete()) {
                    return null;
                }
            }

            memberRewardRepository.save(MemberReward.createMemberReward(member, COMPLETE_FIRST_MISSION));
            return COMPLETE_FIRST_MISSION;
        }
        return null;
    }

    public Reward createReward_Reward(Member member) {
        if (!memberRewardRepository.existsByMemberAndReward(member, COMPLETE_FIRST_MISSION)) {
            if (memberRewardRepository.countMemberRewardByMember(member) == 0L) {
                memberRewardRepository.save(MemberReward.createMemberReward(member, EARN_FIVE_REWARDS));
                return EARN_FIVE_REWARDS;
            }
        }
        return null;
    }

    public List<RewardResponse> getMyRewardList(Long memberId) {
        return memberRewardRepository.findByMemberId(memberId);
    }
}