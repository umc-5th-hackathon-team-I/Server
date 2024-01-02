package com.teami.domain.mission.entity;

import com.teami.domain.calendar.entitty.Calendar;
import com.teami.domain.member.entitty.Member;
import com.teami.domain.mission.entity.Mission;
import com.teami.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "deleted_at is null")
public class CalendarMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Calendar calendar;
}
