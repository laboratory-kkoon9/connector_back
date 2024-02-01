package com.connector.controller;

import com.connector.domain.Experience;
import com.connector.dto.EducationDto;
import com.connector.dto.ExperienceDto;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public List<ProfileDto> profile() {

        return profileService.profile();
    }
    @GetMapping("/user/{userId}")
    public ProfileDetailDto profileDetail(@PathVariable Long userId) {

        return profileService.profileDetail(userId);
    }
    @GetMapping("/me")
    public ProfileDetailDto profileMe() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();

        return profileService.profileDetail(userId);
    }

    @PutMapping("/experience")
    public void profileExperience(@RequestBody ExperienceDto experienceDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();

        profileService.profileExperience(userId, experienceDto);
    }

@PutMapping("/education")
    public void profileEducation(@RequestBody EducationDto educationDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();

        profileService.profileEducation(userId, educationDto);
    }



}
