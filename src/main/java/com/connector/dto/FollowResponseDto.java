package com.connector.dto;

import com.connector.domain.Follow;
import com.connector.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Schema(description = "유저의 팔로우 목록 조회 응답 DTO")
@Getter
public class FollowResponseDto {
    @Schema(description = "유저가 팔로우하는 유저 목록")
    private final List<FollowDto> follows;
    @Schema(description = "유저를 팔로우하는 유저 목록")
    private final List<FollowDto> followings;

    public FollowResponseDto(List<FollowDto> follows, List<FollowDto> followings) {
        this.follows = follows;
        this.followings = followings;
    }

    public static FollowResponseDto of(
        List<Follow> follows,
        List<Follow> followings,
        Map<Long, User> users
    ) {
        return new FollowResponseDto(
            followings.stream().map(follow -> FollowDto.of(follow, users.get(follow.getUserId()))).toList(),
            follows.stream().map(follow -> FollowDto.of(follow, users.get(follow.getTargetUserId()))).toList()
        );
    }
}
