package com.connector.controller;

import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileDto> getProfiles() {
        List<ProfileDto> profiles = profileService.getProfiles();
        return profiles;
    }

    @GetMapping("/user/{userId}")
    public ProfileDetailDto getProfileById(
            @PathVariable(value = "userId") final Long userId
    ) {
        ProfileDetailDto profileDetailDto = profileService.getProfileById(userId);
        return profileDetailDto;
    }
}