package com.teami.domain.calendar.repository;

import com.teami.domain.calendar.entitty.Calendar;
import com.teami.domain.member.entitty.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Optional<Calendar> findByMemberAndIsComplete(Member member, boolean isComplete);
}
