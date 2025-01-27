package com.connector.controller;

import com.connector.dto.RegisterDto;
import com.connector.dto.TokenResponseDto;
import com.connector.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "회원가입 API", description = "회원가입을 한다.<br>request : RegisterDto <br>response : TokenResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = TokenResponseDto.class))
            )
        }
    )
    public TokenResponseDto register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
