package com.connector.controller;

import com.connector.dto.LoginDto;
import com.connector.dto.TokenResponseDto;
import com.connector.dto.UserDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저 정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "내 유저 정보 API", description = "나의 유저 정보를 조회한다.<br>response : UserDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
            )
        }
    )
    public UserDto getAuth() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return userService.getAuth(userId);
    }

    @PostMapping
    @Operation(summary = "로그인 API", description = "로그인을 한다.<br>request : LoginDto <br>response : TokenResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = TokenResponseDto.class))
            )
        }
    )
    public TokenResponseDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
