package com.connector.controller;

import com.connector.dto.LoginDto;
import com.connector.dto.TokenResponseDto;
import com.connector.dto.UserDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping
    public UserDto getAuth() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return userService.getAuth(userId);
    }

    @PostMapping
    public TokenResponseDto login(@RequestBody LoginDto loginDto) {
        return new TokenResponseDto(userService.login(loginDto));
    }
}