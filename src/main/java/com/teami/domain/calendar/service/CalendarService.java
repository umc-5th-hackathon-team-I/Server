package com.teami.domain.calendar.service;

import com.teami.domain.calendar.dto.response.CalendarMissionIdResponse;
import com.teami.domain.calendar.dto.response.CalendarMissionResponse;
import com.teami.domain.calendar.dto.response.CalendarMissionsResponse;
import com.teami.domain.calendar.entitty.Calendar;
import com.teami.domain.calendar.entitty.CalendarMission;
import com.teami.domain.calendar.mapper.CalendarMapper;
import com.teami.domain.calendar.repository.CalendarMissionRepository;
import com.teami.domain.calendar.repository.CalendarRepository;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.member.service.MemberService;
import com.teami.domain.mission.dto.response.NewMissionResponse;
import com.teami.domain.mission.service.MissionService;
import com.teami.domain.reward.service.RewardService;
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final CalendarMissionRepository calendarMissionRepository;
    private final CalendarMapper calendarMapper;
    private final MissionService missionService;
    private final MemberService memberService;
    private final RewardService rewardService;

    @Transactional
    public CalendarMissionsResponse createCalendar(Long memberId) {
        Member loginUser = memberService.findMemberById(memberId);

        // 이전 캘린더가 다 수행되지 않았을 경우 예외 처리
        Optional<Calendar> oldCalendar = calendarRepository.findByMemberAndIsComplete(loginUser, false);
        if (oldCalendar.isPresent()) throw new ExceptionHandler(ErrorStatus.CALENDAR_NOT_FINISHED);

        Calendar newCalendar = calendarRepository.save(
                calendarMapper.toEntity(loginUser));
        // 캘린더 내의 모든 미션들 생성
        try {
            List<NewMissionResponse> missions = missionService.generateMission();
            return calendarMapper.toCalendarMissionsResponse(loginUser, newCalendar, toCalendarMissionsResponse(loginUser, missions, newCalendar));

        } catch (Exception e) {
            throw new ExceptionHandler(ErrorStatus.CREATE_MISSION_FAILED);
        }
    }

    @Transactional
    public CalendarMissionsResponse getCalendar(Long memberId) {
        Member loginUser = memberService.findMemberById(memberId);

        Optional<Calendar> calendar = calendarRepository.findByMemberAndIsComplete(loginUser, false);
        if(calendar.isEmpty()) {
            // TODO : 캘린더가 없는 경우 생성 새로 생성
        }

        List<CalendarMission> missions = calendarMissionRepository.findAllByCalendarOrderByDate(calendar.get());
        return calendarMapper.toCalendarMissionsResponse(loginUser, calendar.get(), calendarMapper.toCalendarMissionResponseList(missions));
    }

    @Transactional
    public CalendarMissionIdResponse completeMission(Long memberId, Long missionId) {
        Member loginUser = memberService.findMemberById(memberId);
        CalendarMission calendarMission = findCalendarMissionById(missionId);

        if(!loginUser.getId().equals(calendarMission.getCalendar().getMember().getId())) {
            throw new ExceptionHandler(ErrorStatus.NO_AUTHORIZATION);
        }
        if(calendarMission.isComplete()) {
            throw new ExceptionHandler(ErrorStatus.MISSION_ALREADY_COMPLETED);
        }

        calendarMission.completeMission();

        rewardService.createReward_Mission1(loginUser);
        rewardService.createReward_Mission2(loginUser);

        return new CalendarMissionIdResponse(calendarMission.getId());
    }

    private List<CalendarMissionResponse> toCalendarMissionsResponse(Member member, List<NewMissionResponse> missions, Calendar calendar) {
        LocalDate now = LocalDate.now();
        List<CalendarMissionResponse> calendarMissions = new ArrayList<>();
        for(int i = 0; i < 30;i++) {
            calendarMissions.add(toCalendarMissionResponse(missions.get(i), now.plusDays(i), calendar));
        }
        return calendarMissions;
    }

    private CalendarMissionResponse toCalendarMissionResponse(NewMissionResponse mission, LocalDate date, Calendar calendar) {
        CalendarMission newCalendarMission = calendarMissionRepository.save(
                calendarMapper.toCalendarMission(
                        mission.getMission(), date, false, calendar
                )
        );
        return new CalendarMissionResponse(newCalendarMission.getId(), newCalendarMission.getContent(), newCalendarMission.getDate(), newCalendarMission.isComplete());
    }

    public CalendarMission findCalendarMissionById(Long missionId) {
        Optional<CalendarMission> calendarMission = calendarMissionRepository.findById(missionId);
        if(calendarMission.isEmpty()) {
            throw new ExceptionHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        return calendarMission.get();
    }

    public CalendarMission findCalendarMissionByCalendarIdAndDate(Long calendarId, LocalDate date) {
        return calendarMissionRepository.findCalendarMissionByCalendarIdAndDate(calendarId, date);
    }

    public List<CalendarMission> find5CalendarMissions(Long calendarId, LocalDate now) {
        List<CalendarMission> calendarMissionList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            now = now.minusDays(1);
            calendarMissionList.add(calendarMissionRepository.findCalendarMissionByCalendarIdAndDate(calendarId, now));
        }

        return calendarMissionList;
    }
}