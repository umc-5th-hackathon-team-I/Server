package com.teami.domain.calendar.controller;

import com.teami.domain.calendar.dto.response.CalendarMissionIdResponse;
import com.teami.domain.calendar.dto.response.CalendarMissionsResponse;
import com.teami.domain.calendar.service.CalendarService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = " Calendar API", description = "캘린더 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    @Operation(summary = "캘린더 생성 API")
    public ApiResponse<CalendarMissionsResponse> createCalendar(@RequestParam Long memberId, @RequestParam LocalDate startDate) {
        return ApiResponse.onSuccess(calendarService.createCalendar(memberId, startDate));
    }

    @GetMapping
    @Operation(summary = "캘린더 조회 API")
    public ApiResponse<CalendarMissionsResponse> getCalendar(@RequestParam Long memberId) {
        return ApiResponse.onSuccess(calendarService.getCalendar(memberId));
    }

    @PostMapping("/mission/complete")
    @Operation(summary = "캘린더 미션 성공 API")
    public ApiResponse<CalendarMissionIdResponse> completeMission(@RequestParam Long memberId, @RequestParam Long missionId) {
        return ApiResponse.onSuccess(calendarService.completeMission(memberId, missionId));
    }

}
