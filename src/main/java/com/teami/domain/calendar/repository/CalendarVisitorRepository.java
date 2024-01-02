package com.teami.domain.calendar.repository;

import com.teami.domain.calendar.entitty.CalendarVisitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarVisitorRepository extends JpaRepository<CalendarVisitor, Long> {

}
