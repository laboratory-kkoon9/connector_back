package com.connector.controller;

import com.connector.dto.LoginDto;
import com.connector.dto.ProfileDetailDto;
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

@Tag(name="권 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "권한 부여 API", description = "권한을 부여한다.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProfileDetailDto.class))
                    )
            }
    )
    public UserDto getAuth() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return userService.getAuth(userId);
    }

    @PostMapping
    @Operation(summary = "로그인 API", description = "로그인을 한다.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProfileDetailDto.class))
                    )
            }
    )
    public TokenResponseDto login(
            @RequestBody LoginDto loginDto
    ) {
        return userService.login(loginDto);
    }
}