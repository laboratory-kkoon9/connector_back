package com.connector.service;

import com.connector.dto.LoginDto;
import com.connector.dto.TokenResponseDto;
import com.connector.dto.UserDto;
import com.connector.global.exception.BadRequestException;
import com.connector.domain.User;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenDto;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TokenManager tokenManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponseDto register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("User already exists");
        }
        User user = userRepository.save(User.builder()
            .name(registerDto.getName())
            .email(registerDto.getEmail())
            .password(passwordEncoder.encode(registerDto.getPassword()))
            .build()
        );
        return toTokenResponseDto(user);
    }

    private TokenResponseDto toTokenResponseDto(User user) {
        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        return tokenManager.generateToken(tokenDto);
    }

    @Transactional(readOnly = true)
    public UserDto getAuth(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .build();
    }

    @Transactional(readOnly = true)
    public TokenResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                () -> new BadRequestException("Invalid Credentials")
        );

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        return toTokenResponseDto(user);
    }
}
