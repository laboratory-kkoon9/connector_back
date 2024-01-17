package com.connector.controller;

import com.connector.dto.RegisterDto;
import com.connector.dto.TokenResponseDto;
import com.connector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    @PostMapping("/users")
    public TokenResponseDto register(@RequestBody RegisterDto registerDto) {

        return userService.register(registerDto);
    }



}
