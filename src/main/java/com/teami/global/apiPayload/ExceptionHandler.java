package com.teami.global.apiPayload;

import com.teami.global.apiPayload.code.BaseErrorCode;
import com.teami.global.apiPayload.exception.GeneralException;

public class ExceptionHandler extends GeneralException {
    public ExceptionHandler(BaseErrorCode code) {
        super(code);
    }
}
