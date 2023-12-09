package com.connector.controller;

import com.connector.dto.ProfileDto;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/api/profile")
    public List<ProfileDto> profileList() {
        return profileService.getProfileList();
    }

    @GetMapping("/api/profile/user/{userId}")
    public ProfileDto profileDetail(@PathVariable("userId") Long userId) {
        return profileService.getProfileDetail(userId);
    }

}
