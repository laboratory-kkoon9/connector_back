package com.connector.service;

import com.connector.domain.Follow;
import com.connector.domain.User;
import com.connector.dto.FollowResponseDto;
import com.connector.global.exception.BadRequestException;
import com.connector.repository.FollowRepository;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public FollowResponseDto getUserFollows(Long userId) {
        List<Follow> followings = followRepository.findByUserId(userId);
        List<Follow> follows = followRepository.findByTargetUserId(userId);

        Set<Long> userIds = Stream.concat(
            followings.stream().flatMap(follow -> Stream.of(follow.getTargetUserId(), follow.getUserId())),
            follows.stream().flatMap(follow -> Stream.of(follow.getTargetUserId(), follow.getUserId()))
        ).collect(Collectors.toSet());

        Map<Long, User> users = userRepository.findAllById(userIds)
            .stream()
            .collect(Collectors.toMap(
                User::getId, Function.identity(), (existing, replacement) -> existing)
            );

        return FollowResponseDto.of(follows, followings, users);
    }

    @Transactional
    public void followUser(Long userId, Long targetUserId) {
        if (userId.equals(targetUserId)) {
            throw new BadRequestException("본인 계정은 팔로우할 수 없습니다.");
        }

        if (followRepository.existsByUserIdAndTargetUserId(userId, targetUserId)) {
            throw new BadRequestException("이미 팔로우한 상태입니다.");
        }

        followRepository.save(Follow.builder()
            .userId(userId)
            .targetUserId(targetUserId)
            .build());
    }

    @Transactional
    public void cancelFollowUser(Long userId, Long targetUserId) {
        if (userId.equals(targetUserId)) {
            throw new BadRequestException("본인 계정은 팔로우 취소를 할 수 없습니다.");
        }

        Follow follow = followRepository.findByUserIdAndTargetUserId(userId, targetUserId).orElseThrow(
            () -> new BadRequestException("팔로우하지 않은 유저는 취소할 수 없습니다.")
        );


        followRepository.delete(follow);
    }
}
