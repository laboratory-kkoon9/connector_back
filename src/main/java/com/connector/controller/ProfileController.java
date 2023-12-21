package com.connector.controller;

import com.connector.dto.EducationDto;
import com.connector.dto.ExperienceDto;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.dto.UpsertProfileDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ProfileService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/me")
    public ProfileDetailDto getMyProfile() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        ProfileDetailDto profileDetailDto = profileService.getProfileById(userId);
        return profileDetailDto;
    }

    @PostMapping
    public void upsertProfile(@RequestBody UpsertProfileDto profileDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.upsertProfile(userId, profileDto);
    }

    @PutMapping("/experience")
    public void addExperience(@RequestBody ExperienceDto experienceDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.addExperience(userId, experienceDto);
    }

    @DeleteMapping("/experience/{experience_id}")
    public void deleteExperience(
            @PathVariable(value = "experience_id") final Long experienceId
    ) {
        profileService.deleteExperience(experienceId);
    }

    @PutMapping("/education")
    public void addEducation(@RequestBody EducationDto educationDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.addEducation(userId, educationDto);
    }

    @DeleteMapping("/education/{education_id}")
    public void deleteEducation(
            @PathVariable(value = "education_id") final Long educationId
    ) {
        profileService.deleteEducation(educationId);
    }
}
