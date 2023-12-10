package com.connector.controller;

import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDto> profileList() {
        return profileService.getProfileList();
    }

    @GetMapping("/user/{userId}")
    public ProfileDetailDto profileDetail(@PathVariable("userId") Long userId) {
        return profileService.getProfileDetail(userId);
    }

}
