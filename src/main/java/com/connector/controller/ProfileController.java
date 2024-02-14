package com.connector.controller;

import com.connector.domain.Experience;
import com.connector.domain.Profile;
import com.connector.dto.*;
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

    @GetMapping
    public List<ProfileDto> profiles() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();

        return profileService.profile(userId);
    }
    @PostMapping
    public void profileUpdate(@RequestBody ProfileUpdateDto profileUpdateDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();

        profileService.myProfileUpdate(profileUpdateDto, userId);
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
    @DeleteMapping("/experience/{experience_id}")
    public void profileExperience(@PathVariable Long experience_id) {

        profileService.profileExperienceDelete(experience_id);
    }
    @DeleteMapping("/education/{education_id}")
    public void profileEducation_id(@PathVariable Long education_id) {

        profileService.profileEducation_idDelete(education_id);
    }



}
