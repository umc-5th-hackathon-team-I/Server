package com.teami.domain.calendar.service;

import com.teami.domain.calendar.dto.response.CalendarIdResponse;
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
import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public CalendarMissionsResponse createCalendar(Long memberId, LocalDate startDate) {
        Member loginUser = memberService.findMemberById(memberId);

        // 이전 캘린더가 다 수행되지 않았을 경우 예외 처리
        Optional<Calendar> oldCalendar = calendarRepository.findByMemberAndIsComplete(loginUser, false);
        if (oldCalendar.isPresent()) throw new ExceptionHandler(ErrorStatus.CALENDAR_NOT_FINISHED);

        Calendar newCalendar = calendarRepository.save(
                calendarMapper.toEntity(loginUser));
        // 캘린더 내의 모든 미션들 생성
        try {
            List<NewMissionResponse> missions = missionService.generateMission();
            return calendarMapper.toCalendarMissionsResponse(newCalendar, toCalendarMissionsResponse(missions, newCalendar));

        } catch (Exception e) {
            throw new ExceptionHandler(ErrorStatus.CREATE_MISSION_FAILED);
        }

    }

    private List<CalendarMissionResponse> toCalendarMissionsResponse(List<NewMissionResponse> missions, Calendar calendar) {
        LocalDate now = LocalDate.now();
        List<CalendarMissionResponse> calendarMissions = new ArrayList<>();
        for(int i = 0; i< 30;i++) {
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

        return new CalendarMissionResponse(newCalendarMission.getId(), newCalendarMission.getContent(), newCalendarMission.isComplete());
    }
}
