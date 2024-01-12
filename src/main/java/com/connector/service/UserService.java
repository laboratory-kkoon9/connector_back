package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.*;
import com.connector.global.exception.BadRequestException;
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

    @Transactional
    public TokenResponseDto join(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("User already exists");
        }
        User user = userRepository.save(registerDto.toEntity());
        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        return tokenManager.generateToken(tokenDto);
    }

    public UserDto getAuth(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        return UserDto.getUserDto(user);
    }

    public TokenResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                () -> new BadRequestException("Invalid Credentials")
        );
        if (!user.checkPassword(loginDto.getPassword())) throw new BadRequestException("Check Password");

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        return tokenManager.generateToken(tokenDto);
    }
}
