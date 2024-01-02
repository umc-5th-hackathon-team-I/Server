package com.teami.domain.test.mapper;


import com.teami.domain.test.dto.TestResponse;

public class TestMapper {
    public static TestResponse.TestDTO toTestResponse() {
        return TestResponse.TestDTO.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .build();
    }

    public static TestResponse.TestExceptionDTO toTestExceptionResponse(Integer flag) {
        return TestResponse.TestExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
