package com.teami.domain.calendar.repository;

import com.teami.domain.calendar.entitty.CalendarVisitor;
import com.teami.domain.member.dto.response.VisitorCommentRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarVisitorRepository extends JpaRepository<CalendarVisitor, Long> {


    List<CalendarVisitor> findByOwnerId(Long memberId);
}
