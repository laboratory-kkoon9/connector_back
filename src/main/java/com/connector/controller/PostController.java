package com.connector.controller;

import com.connector.dto.*;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    @Operation(summary = "게시물 작성 API", description = "게시물을 작성한다.<br>request : CreatePostDto <br>response : PostDetailResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = PostDetailResponseDto.class))
            )
        }
    )
    public PostDetailResponseDto createPost(@RequestBody CreatePostDto postDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return postService.createPost(userId, postDto);
    }

    @GetMapping
    @Operation(summary = "게시물 목록 조회 API", description = "게시물 목록을 조회한다. <br>response : PostResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = PostResponseDto.class))
            )
        }
    )
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{post_id}")
    @Operation(summary = "게시물 상세 조회 API", description = "특정 게시물을 조회한다. <br>response : PostDetailResponseDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PostDetailResponseDto.class)
            )
        }
    )
    public PostDetailResponseDto getPostById(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
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
    @Operation(
        summary = "게시물 좋아요 API",
        description = "게시물에 좋아요를 누른다."
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void likePost(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId
    ) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        postService.likePost(userId, postId);
    }

    @PutMapping("/unlike/{post-id}")
    @Operation(
        summary = "게시물 좋아요 취소 API",
        description = "게시물에 좋아요 누른 걸 취소한다."
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void unlikePost(
        @Parameter(name = "post-id", description = "게시물 ID", in = ParameterIn.PATH)
        @PathVariable(value = "post-id") final Long postId
    ) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        postService.unlikePost(userId, postId);
    }

    @PostMapping("/comment/{post-id}")
    @Operation(
        summary = "댓글달기 API",
        description = "게시물에 댓글을 추가한다."
    )
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CommentResponseDto.class))
            )
        }
    )
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
    @Operation(
        summary = "댓글 삭제 API",
        description = "게시물 댓글을 삭제한다."
    )
    @ApiResponse(
        responseCode = "200"
    )
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
