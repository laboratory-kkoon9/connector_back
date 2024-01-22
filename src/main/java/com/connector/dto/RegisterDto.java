package com.connector.dto;

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
}