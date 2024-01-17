package com.connector.dto;

import com.connector.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class UserDto {
    @Schema(description = "유저 ID")
    private Long id;
    @Schema(description = "유저 이름", example = "Eric")
    private String name;
    @Schema(description = "유저 이메일", example = "eric@naver.com")
    private String email;
    @Schema(description = "유저 아바타")
    private String avatar;

    public static UserDto getUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatar());
    }
}
