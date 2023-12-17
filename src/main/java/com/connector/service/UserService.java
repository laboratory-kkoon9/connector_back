package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenDto;
import com.connector.global.exception.DuplicateUserEmailException;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    public void register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateUserEmailException();
        }
        User user = userRepository.save(registerDto.toEntity());

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        tokenManager.generateToken(tokenDto);
    }
}
