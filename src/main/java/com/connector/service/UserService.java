package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.TokenDto;
import com.connector.global.exception.BadRequestException;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    public TokenResponseDto register(RegisterDto registerDto) {
        /* 익셉션 */
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BadRequestException("User already exists");
        }

        /* 저장 */
        User user = userRepository.save(registerDto.toEntity());

        /* 토큰 */
        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        /* 얻은 토큰 리턴 */
        return tokenManager.generateToken(tokenDto);
    }

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

    public TokenResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(
                () -> new BadRequestException("Invalid Credentials")
        );

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();
        return tokenManager.generateToken(tokenDto);
    }



}
