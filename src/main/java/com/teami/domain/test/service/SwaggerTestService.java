package com.teami.domain.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwaggerTestService {

    public void CheckFlag(Integer flag) {
        if(flag == 1)
            throw new IllegalArgumentException();
    }
}
