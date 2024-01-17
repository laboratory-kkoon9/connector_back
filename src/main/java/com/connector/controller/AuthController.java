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
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;


    @GetMapping
    public UserDto getAuth() {
        TokenContext context = TokenContextHolder.getContext(); // 토큰을 가지고 있는지 검사?
        Long userId = context.getUserId();
        return userService.getAuth(userId);
    }

    @PostMapping
    public TokenResponseDto login(@RequestBody LoginDto loginDto) {

        return userService.login(loginDto);
    }

}
