package com.connector.service;

import com.connector.domain.Posts;
import com.connector.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {

    PostsRepository postsRepository;

    public Posts saveCommunit() {



        return postsRepository.save(
                Posts.builder()
                .content(saveCommunit().getContent())
                .build());
    }
}
