package com.teami.global.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {
    private final Boolean isSuccess;
    private final String message;
    private final HttpStatus httpStatus;
    private final String code;
}
