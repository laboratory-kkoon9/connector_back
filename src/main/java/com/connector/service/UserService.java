package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenDto;
import com.connector.dto.UserDto;
import com.connector.global.exception.DuplicateUserEmailException;
import com.connector.global.exception.NotUserException;
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

        User user = convertToEntity(registerDto); // RegisterDto를 User 엔티티로 변환
        userRepository.save(user);

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build(); // 저장된 사용자의 ID를 사용하여 TokenDto 객체를 생성
        return tokenManager.generateToken(tokenDto); // JWT 토큰을 생성
    }

    public UserDto getAuth(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotUserException::new); // 사용자가 존재하지 않으면 NotUserException 발생
        return createUserDto(user); // User 엔티티를 UserDto로 변환
    }

    // User 엔티티를 UserDto 객체로 변환하는 메소드
    private UserDto createUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .build();
    }

    // RegisterDto를 User 엔티티로 변환하는 메소드
    private User convertToEntity(RegisterDto registerDto) {
        return User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .build();
    }
}