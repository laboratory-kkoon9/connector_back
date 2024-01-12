package com.connector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class LoginDto {
    @Schema(description = "유저 이메일", example = "eric@naver.com")
    @Email
    private String email;
    private String password;
}
