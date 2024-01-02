package com.teami.domain.calendar.controller;

import com.teami.domain.calendar.dto.response.CalendarIdResponse;
import com.teami.domain.calendar.dto.response.CalendarMissionsResponse;
import com.teami.domain.calendar.service.CalendarService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
