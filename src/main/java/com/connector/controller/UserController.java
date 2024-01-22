package com.connector.controller;

import com.connector.dto.RegisterDto;
import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Map<String, String> register(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto.toString());
        String token = userService.register(registerDto);
        return Collections.singletonMap("token", token);
    }
}
