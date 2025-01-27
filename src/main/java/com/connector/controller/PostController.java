package com.connector.controller;

import com.connector.dto.CommentResponseDto;
import com.connector.dto.CreateCommentDto;
import com.connector.dto.CreatePostDto;
import com.connector.dto.PostDetailResponseDto;
import com.connector.dto.PostResponseDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostDetailResponseDto createPost(@RequestBody CreatePostDto postDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return postService.createPost(userId, postDto);
    }

    @GetMapping
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{post_id}")
    public PostDetailResponseDto getPostById(
        @PathVariable(value = "post_id") final Long postId
    ) {
        return postService.getPostById(postId);
    }

    @DeleteMapping("/{post-id}")
    @Operation(
        summary = "게시물 삭제 API",
        description = "나의 게시물을 삭제한다."
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void deletePost(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId
    ) {
        postService.deletePost(postId);
    }

    @PutMapping("/like/{post-id}")
    public void likePost(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId
    ) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        postService.likePost(userId, postId);
    }

    @PutMapping("/unlike/{post-id}")
    public void unlikePost(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId
    ) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        postService.unlikePost(userId, postId);
    }

    @PostMapping("/comment/{post-id}")
    public List<CommentResponseDto> addComment(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId,
        @RequestBody CreateCommentDto commentDto
    ) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return postService.addComment(userId, postId, commentDto);
    }

    @DeleteMapping("/comment/{post-id}/{comment-id}")
    public void removeComment(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId,
        @Parameter(name = "comment-id", description = "댓글 ID", in = ParameterIn.PATH)
        @PathVariable(value = "comment-id") final Long commentId
    ) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        postService.removeComment(userId, postId, commentId);
    }
}
