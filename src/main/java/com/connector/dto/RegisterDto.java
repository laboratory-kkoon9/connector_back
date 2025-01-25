package com.connector.dto;

import com.connector.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@Schema(description = "유저의 토큰")
public class RegisterDto {
    @Schema(description = "유저의 닉네임")
    private String name;
    @Schema(description = "유저의 이메일")
    private String email;
    @Schema(description = "유저의 비밀번호")
    private String password;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
