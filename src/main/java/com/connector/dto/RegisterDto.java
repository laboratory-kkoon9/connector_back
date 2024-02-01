package com.connector.dto;

import com.connector.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class RegisterDto {
    private String name;
    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}