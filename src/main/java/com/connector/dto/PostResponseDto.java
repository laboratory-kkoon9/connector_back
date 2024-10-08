package com.connector.dto;

import com.connector.repository.model.GetPostResponseModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String text;
    private String name;
    private String avatar;
    private Long userId;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDateTime date;

    @Builder
    public PostResponseDto(Long id, String text, String name, String avatar, Long userId, Integer likeCount, Integer commentCount, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.avatar = avatar;
        this.userId = userId;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.date = date;
    }

    public static PostResponseDto of(GetPostResponseModel model) {
        return PostResponseDto.builder()
                .id(model.getPost().getId())
                .text(model.getPost().getContent())
                .name(model.getUser().getName())
                .avatar(model.getUser().getAvatar())
                .userId(model.getUser().getId())
                .likeCount(model.getPost().getLikes().size())
                .commentCount(model.getPost().getComments().size())
                .date(model.getPost().getCreatedAt())
                .build();
    }
}
