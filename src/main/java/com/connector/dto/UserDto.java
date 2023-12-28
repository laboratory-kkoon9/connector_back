package com.connector.dto;

import com.connector.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String avatar;

    public static UserDto getUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatar());
    }
}
