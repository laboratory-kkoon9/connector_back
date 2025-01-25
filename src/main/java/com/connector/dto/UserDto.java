package com.connector.dto;

import com.connector.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


@Getter
@Schema(description = "유저 정보 DTO")
public class UserDto {
    @Schema(description = "유저 id")
    private Long id;
    @Schema(description = "유저 닉네임")
    private String name;
    @Schema(description = "유저 이메일")
    private String email;
    @Schema(description = "유저 프로필 url")
    private String avatar;

    @Builder
    public UserDto(Long id, String name, String email, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public static UserDto from(User user) {
        return UserDto.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .avatar(user.getAvatar())
            .build();
    }
}
