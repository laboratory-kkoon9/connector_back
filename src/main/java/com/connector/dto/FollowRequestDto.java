package com.connector.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "팔로우하기 요청 DTO")
public class FollowRequestDto {
    @Schema(description = "팔로우하려는 상대방 uid")
    private Long userId;
}
