package com.teami.domain.calendar.entitty;

import com.teami.domain.member.entitty.Member;
import com.teami.domain.mission.entity.Mission;
import com.teami.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean isComplete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Calendar calendar;

    public void completeMission() {
        this.isComplete = true;
    }
}
