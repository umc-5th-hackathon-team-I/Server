package com.teami.domain.calendar.repository;

import com.teami.domain.calendar.entitty.CalendarMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarMissionRepository extends JpaRepository<CalendarMission, Long> {
}
