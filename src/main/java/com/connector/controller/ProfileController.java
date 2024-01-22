package com.connector.controller;

import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/user/{userId}")
    public ProfileDetailDto getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId);
    }
}
