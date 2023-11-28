package com.connector.service;

import com.connector.config.exception.DuplicateUserEmailException;
import com.connector.dto.RegisterDto;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateUserEmailException();
        }

        return "token";
    }
}
