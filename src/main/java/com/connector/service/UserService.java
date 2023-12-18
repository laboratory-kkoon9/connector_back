package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.LoginDto;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenDto;
import com.connector.dto.UserDto;
import com.connector.global.exception.DuplicateUserEmailException;
import com.connector.global.exception.InvalidUserEmailException;
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

    @Transactional
    public String join(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateUserEmailException();
        }
        User user = userRepository.save(registerDto.toEntity());

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        return tokenManager.generateToken(tokenDto);
    }

    public UserDto getAuth(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotUserException::new);
        return UserDto.getUserDto(user);
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(InvalidUserEmailException::new);

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        return tokenManager.generateToken(tokenDto);
    }
}
