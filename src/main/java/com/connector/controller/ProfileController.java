package com.connector.controller;

import com.connector.domain.Profile;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public List<Profile> getProfiles() {
        List<Profile> profiles = profileService.getProfiles();
        return profiles;
    }
}
