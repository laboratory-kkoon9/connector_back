package com.connector.controller;

import com.connector.dto.*;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/user/{userId}")
    public ProfileDetailDto getOneProfile(@PathVariable("userId") Long userId) {
        return profileService.getOneProfile(userId);
    }

    @GetMapping("/me")
    public ProfileDetailDto getMyProfile() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return profileService.getOneProfile(userId);
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
    public void deleteExperience(@PathVariable("experience_id") Long experienceId) {
        profileService.deleteExperience(experienceId);
    }

    @PutMapping("/education")
    public void addEducation(@RequestBody EducationDto educationDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.addEducation(userId, educationDto);
    }

    @DeleteMapping("/education/{education_id}")
    public void deleteEducation(@PathVariable("education_id") Long educationId) {
        profileService.deleteEducation(educationId);
    }

    @GetMapping("/github/{github_id}")
    public List<GithubResponseItemDto> getGitRepositories(
            @PathVariable(value = "github_id") final String gitHubId
    ) {
        return profileService.getGitRepositories(gitHubId);
    }
}
