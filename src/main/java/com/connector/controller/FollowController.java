package com.connector.controller;

import com.connector.dto.FollowRequestDto;
import com.connector.dto.FollowResponseDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "팔로우 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "해당 유저의 팔로우 목록 조회 API", description = "해당 유저의 팔로우 목록을 조회한다. <br>response : FollowResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = FollowResponseDto.class))
            )
        }
    )
    public FollowResponseDto getUserFollows(
        @PathVariable(value = "userId") final Long userId
    ) {
        return followService.getUserFollows(userId);
    }

    @GetMapping("/me")
    @Operation(summary = "본인의 팔로우 목록 조회 API", description = "본인의 팔로우 목록을 조회한다. <br>response : FollowResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = FollowResponseDto.class))
            )
        }
    )
    public FollowResponseDto getMyFollows() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return followService.getUserFollows(userId);
    }


    @PostMapping
    @Operation(summary = "팔로우하기 API", description = "팔로우를 한다.<br>request : FollowRequestDto")
    @ApiResponse(
        responseCode = "200"
    )
    public void follow(@RequestBody FollowRequestDto followDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        followService.followUser(userId, followDto.getUserId());
    }

    @DeleteMapping
    @Operation(summary = "팔로우 취소하기 API", description = "팔로우를 취소한다.<br>request : FollowRequestDto")
    @ApiResponse(
        responseCode = "200"
    )
    public void cancelFollow(@RequestBody FollowRequestDto followDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        followService.cancelFollowUser(userId, followDto.getUserId());
    }
}
