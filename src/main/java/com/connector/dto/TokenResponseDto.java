package com.connector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "회원가입/로그인 응답 DTO")
public class TokenResponseDto {
    @Schema(description = "유저의 토큰")
    private String token;
    public TokenResponseDto(String token) {
        this.token = token;
    }
}
