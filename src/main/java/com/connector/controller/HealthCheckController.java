package com.connector.controller;

import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    private final UserService userService;

    @GetMapping("/ping")
    public String healthCheck() {
        return "ok";
    }
}
