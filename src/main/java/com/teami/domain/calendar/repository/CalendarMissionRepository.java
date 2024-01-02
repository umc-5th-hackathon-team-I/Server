package com.teami.domain.calendar.repository;

import com.teami.domain.calendar.entitty.Calendar;
import com.teami.domain.calendar.entitty.CalendarMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarMissionRepository extends JpaRepository<CalendarMission, Long> {
    List<CalendarMission> findAllByCalendarOrderByDate(Calendar calendar);
    CalendarMission findCalendarMissionByCalendarIdAndDate(Long calendarId, LocalDate date);
}
