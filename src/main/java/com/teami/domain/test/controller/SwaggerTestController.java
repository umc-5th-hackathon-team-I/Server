package com.teami.domain.test.controller;

import com.teami.domain.test.dto.TestRequest;
import com.teami.domain.test.dto.TestResponse;
import com.teami.domain.test.mapper.TestMapper;
import com.teami.domain.test.service.SwaggerTestService;
import com.teami.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class SwaggerTestController {
    private final SwaggerTestService swaggerTestService;

    @PostMapping
    public void postTestController(@RequestBody TestRequest request) {
        // pass
    }

    @GetMapping
    public ApiResponse<TestResponse.TestDTO> testAPI() {
        return ApiResponse.onSuccess(TestMapper.toTestResponse());
    }

    @GetMapping("/exception")
    public ApiResponse<TestResponse.TestExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        swaggerTestService.CheckFlag(flag);
        return ApiResponse.onSuccess(TestMapper.toTestExceptionResponse(flag));
    }
}
