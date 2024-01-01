package com.connector.service;

import com.connector.dto.CreatePostDto;
import com.connector.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void createPost(Long userId, CreatePostDto postDto) {
        postRepository.save(postDto.toEntity(userId));
    }
}
