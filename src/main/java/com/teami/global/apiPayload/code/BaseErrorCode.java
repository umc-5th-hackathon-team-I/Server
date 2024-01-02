package com.teami.global.apiPayload.code;

public interface BaseErrorCode {
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();
}