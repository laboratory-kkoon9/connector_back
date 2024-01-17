package com.connector.repository;

import com.connector.domain.Post;
import com.connector.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> deleteAllByUser(User user);
}