package com.teami.domain.calendar.repository;

import com.teami.domain.calendar.entitty.CalendarVisitor;
import com.teami.domain.member.dto.response.VisitorCommentRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarVisitorRepository extends JpaRepository<CalendarVisitor, Long> {

    List<CalendarVisitor> findByOwnerId(Long memberId);

    @Query(value = "SELECT COUNT(*) FROM calendar_visitor WHERE is_checked = true AND owner_id = :memberId", nativeQuery = true)
    int checkFirstLetterByOwnerId(@Param("memberId")Long memberId);
}
