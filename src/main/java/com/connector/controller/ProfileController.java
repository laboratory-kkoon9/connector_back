package com.connector.controller;

import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/user/{userId}")
    public ProfileDetailDto getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId);
    }

    @GetMapping("/me") // 본인의 프로필조회
    public ProfileDetailDto getMyProfile() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        ProfileDetailDto profileDetailDto = profileService.getProfileByUserId(userId);
        return profileDetailDto;
    }
}

