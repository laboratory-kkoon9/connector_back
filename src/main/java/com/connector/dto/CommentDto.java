package com.connector.dto;

import com.connector.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private String name;
    private String avatar;
    private Long userId;

    public static CommentDto getCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getWriter().getName(),
                comment.getWriter().getAvatar(),
                comment.getWriter().getId());
    }
}
