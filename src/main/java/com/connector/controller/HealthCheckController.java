package com.connector.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "헬스 체크 API")
@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    @GetMapping("/ping")
    @Operation(summary = "헬스 체크 API", description = "서버 상태가 정상이면 200 ok")
    @ApiResponse(responseCode = "200")
    public String healthCheck() {
        return "ok";
    }
}
