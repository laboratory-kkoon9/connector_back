package com.connector.controller;

import com.connector.domain.Profile;
import com.connector.service.ProfileService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getProfiles() {
        List<Profile> profiles = profileService.getProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/user/{userId}")
    public Profile getProfileById(
            @PathVariable(value = "userId") final Long userId
    ) {
        Profile profile = profileService.getProfileById(userId);
        return profile;
    }
}
