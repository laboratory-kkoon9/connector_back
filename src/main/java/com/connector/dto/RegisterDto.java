package com.connector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "회원가입 요청 DTO")
public class RegisterDto {
    @Schema(description = "유저의 닉네임")
    private String name;
    @Schema(description = "유저의 이메일")
    private String email;
    @Schema(description = "유저의 비밀번호")
    private String password;

    public RegisterDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
