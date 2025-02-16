package com.connector.dto;

import com.connector.domain.Follow;
import com.connector.domain.User;
import lombok.Getter;

@Getter
public class FollowDto {
    private Long id;
    private UserDto user;

    public FollowDto(Long id, UserDto user) {
        this.id = id;
        this.user = user;
    }

    public static FollowDto of(Follow follow, User user) {
        return new FollowDto(follow.getId(), UserDto.from(user));
    }
}
