package com.connector.service;

import com.connector.dto.RegisterDto;
import com.connector.global.exception.DuplicateUserEmailException;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateUserEmailException();
        }
        userRepository.save(registerDto.toEntity());
    }
}
