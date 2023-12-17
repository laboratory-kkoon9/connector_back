package com.connector.controller;

import com.connector.dto.RegisterDto;
import com.connector.dto.RegisterResponseDto;
import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public RegisterResponseDto register(@RequestBody RegisterDto registerDto) {
        return new RegisterResponseDto(userService.join(registerDto));
    }
}
