package com.teami.domain.test.service;

import com.teami.global.apiPayload.ExceptionHandler;
import com.teami.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwaggerTestService {

    public void CheckFlag(Integer flag) {
        if(flag == 1)
            throw new ExceptionHandler(ErrorStatus._BAD_REQUEST);
    }
}
