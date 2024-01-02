package com.connector.controller;

import com.connector.dto.*;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="프로필 API")
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
    @Operation(summary = "프로필 상세 조회 API", description = "프로필의 상세 정보를 조회한다.")
    @Parameter(name = "userId", description = "유저 ID", in = ParameterIn.PATH)
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProfileDetailDto.class))
                    )
            }
    )
    public ProfileDetailDto getOneProfile(
            @PathVariable("userId") Long userId
    ) {
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
