package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenDto;
import com.connector.global.exception.DuplicateUserEmailException;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    @Transactional // 메서드 내의 모든 데이터베이스 연산은 하나의 트랜잭션으로 묶는 역활
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) { // 사용자 이메일이 이미 존재하는 확인
            throw new DuplicateUserEmailException();                // 이미 존재할경우 DuplicateUserEmailException 예외를 발생
        }

        User user = userRepository.save(registerDto.toEntity()); // User 엔티티를 데이터베이스에 저장
        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build(); // 저장된 사용자의 ID를 사용하여 TokenDto 객체를 생성
        String token = tokenManager.generateToken(tokenDto); // JWT 토큰을 생성
        return token;
    }
}