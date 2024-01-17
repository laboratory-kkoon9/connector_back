package com.connector.dto;

import com.connector.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private Long id;
    private String text;
    private String name;
    private String avatar;
    private Long userId;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDateTime date;

    public PostDto(Post post) {
        id = post.getId();
        text = post.getContent();
        name = post.getUser().getName();
        avatar = post.getUser().getAvatar();
        userId = post.getUser().getId();
        likeCount = post.getLikes().size();
        commentCount = post.getComments().size();
        date = post.getCreatedAt();
    }
}
