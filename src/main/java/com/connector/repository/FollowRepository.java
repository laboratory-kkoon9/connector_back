package com.connector.repository;

import com.connector.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUserId(Long userId);
    List<Follow> findByTargetUserId(Long targetUserId);
    Boolean existsByUserIdAndTargetUserId(Long userId, Long targetUserId);
    Optional<Follow> findByUserIdAndTargetUserId(Long userId, Long targetUserId);
}
