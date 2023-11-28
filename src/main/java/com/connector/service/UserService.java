package com.connector.service;

import com.connector.config.exception.DuplicateUserEmailException;
import com.connector.config.filter.jwt.JwtTokenManager;
import com.connector.domain.User;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenDto;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenManager jwtTokenManager;
    private final UserRepository userRepository;


    @Transactional
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateUserEmailException();
        }

        User user = userRepository.save(registerDto.toEntity());

        TokenDto tokenDto = TokenDto.builder().userId(user.getId()).build();

        String token = jwtTokenManager.generateToken(tokenDto);

        return token;
    }
}
