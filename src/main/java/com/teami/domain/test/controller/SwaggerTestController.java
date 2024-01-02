package com.teami.domain.test.controller;

import com.teami.domain.test.dto.TestRequest;
import com.teami.domain.test.dto.TestResponse;
import com.teami.domain.test.mapper.TestMapper;
import com.teami.domain.test.service.SwaggerTestService;
import io.swagger.v3.oas.annotations.Hidden;
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
    public void postTestController(@RequestBody TestRequest request) { }

    @GetMapping
    public TestResponse.TestDTO testAPI() {
        return TestMapper.toTestResponse();
    }

    @GetMapping("/exception")
    public TestResponse.TestExceptionDTO exceptionAPI(@RequestParam Integer flag) {
        swaggerTestService.CheckFlag(flag);
        return TestMapper.toTestExceptionResponse(flag);
    }
}
