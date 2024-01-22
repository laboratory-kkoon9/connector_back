package com.connector.controller;

import com.connector.dto.LoginDto;
import com.connector.dto.LoginResponseDto;
import com.connector.dto.UserDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @GetMapping // 본인의 정보조회
    public UserDto getAuth() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return userService.getAuth(userId);
    }

    @PostMapping // 로그인
    public LoginResponseDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}