package com.connector.controller;

import com.connector.dto.RegisterDto;
import com.connector.dto.ResponseDto;
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
    public ResponseDto register(@RequestBody RegisterDto registerDto) {
        return new ResponseDto(userService.join(registerDto));
    }
}
