package com.connector.controller;

import com.connector.dto.RegisterDto;
import com.connector.dto.TokenResponseDto;
import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public TokenResponseDto register(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
