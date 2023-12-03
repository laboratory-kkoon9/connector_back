package com.connector.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String avatar;

    @Builder
    public UserDto(Long id, String name, String email, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }
}
