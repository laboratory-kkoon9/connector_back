package com.connector.controller;

import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/user/{userId}")
    public ProfileDetailDto getOneProfile(@PathVariable("userId") Long userId) {
        return profileService.getOneProfile(userId);
    }

    @PostMapping("/me")
    public ProfileDetailDto getMyProfile() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return profileService.getOneProfile(userId);
    }

}
