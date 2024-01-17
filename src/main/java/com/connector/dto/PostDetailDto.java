package com.connector.dto;

import com.connector.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDetailDto {
    private Long id;
    private String text;
    private String name;
    private String avatar;
    private Long userId;
    private List<CommentDto> comments;
    private LocalDateTime date;

    public PostDetailDto(Post post) {
        id = post.getId();
        text = post.getContent();
        name = post.getUser().getName();
        avatar = post.getUser().getAvatar();
        userId = post.getUser().getId();
        comments = post.getComments().stream().map(CommentDto::getCommentDto).collect(Collectors.toList());
        date = post.getCreatedAt();
    }
}
