package com.connector.controller;

import com.connector.domain.Profile;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/api/profile")
    public List<Profile> profileList() {
        return profileService.getProfiles();
    }

}
