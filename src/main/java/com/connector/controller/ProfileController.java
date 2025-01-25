package com.connector.controller;

import com.connector.dto.EducationDto;
import com.connector.dto.ExperienceDto;
import com.connector.dto.GithubResponseItemDto;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.dto.UpsertProfileDto;
import com.connector.global.context.TokenContext;
import com.connector.global.context.TokenContextHolder;
import com.connector.service.ImageService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "프로필 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final ImageService imageService;

    @GetMapping
    @Operation(summary = "프로필 목록 조회 API", description = "프로필 목록을 조회한다.<br>response : ProfileDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProfileDto.class))
            )
        }
    )
    public List<ProfileDto> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "프로필 상세 조회 API", description = "프로필의 상세 정보를 조회한다.<br>response : ProfileDetailDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProfileDetailDto.class))
            )
        }
    )
    public ProfileDetailDto getProfileById(
        @Parameter(name = "userId", description = "유저 ID", in = ParameterIn.PATH)
        @PathVariable(value = "userId") final Long userId
    ) {
        return profileService.getProfileById(userId);
    }

    @GetMapping("/me")
    @Operation(summary = "내 프로필 조회 API", description = "나의 프로필의 정보를 조회한다.<br>response : ProfileDetailDto")
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ProfileDetailDto.class))
            )
        }
    )
    public ProfileDetailDto getMyProfile() {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        return profileService.getProfileById(userId);
    }

    @PostMapping
    @Operation(
        summary = "프로필 생성 및 수정 API",
        description = "나의 프로필을 생성 혹은 수정한다.<br>(request : UpsertProfileDto)"
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void upsertProfile(@RequestBody UpsertProfileDto profileDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.upsertProfile(userId, profileDto);
    }

    @PutMapping("/experience")
    @Operation(
        summary = "경력 추가 API",
        description = "나의 경력을 추가한다.<br>(request : ExperienceDto)"
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void addExperience(@RequestBody ExperienceDto experienceDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.addExperience(userId, experienceDto);
    }

    @DeleteMapping("/experience/{experience_id}")
    @Operation(
        summary = "경력 제거 API",
        description = "나의 경력을 제거한다."
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void deleteExperience(
        @Parameter(name = "experience_id", description = "경력 ID", in = ParameterIn.PATH)
        @PathVariable(value = "experience_id") final Long experienceId
    ) {
        profileService.deleteExperience(experienceId);
    }

    @PutMapping("/education")
    @Operation(
        summary = "학력 추가 API",
        description = "나의 학력을 추가한다.<br>(request : EducationDto)"
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void addEducation(@RequestBody EducationDto educationDto) {
        TokenContext context = TokenContextHolder.getContext();
        Long userId = context.getUserId();
        profileService.addEducation(userId, educationDto);
    }

    @DeleteMapping("/education/{education_id}")
    @Operation(
        summary = "학력 제거 API",
        description = "나의 학력을 제거한다."
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void deleteEducation(
        @Parameter(name = "education_id", description = "학력 ID", in = ParameterIn.PATH)
        @PathVariable(value = "education_id") final Long educationId
    ) {
        profileService.deleteEducation(educationId);
    }

    @GetMapping("/github/{github_id}")
    @Operation(
        summary = "깃허브의 레포지터리 목록 조회 API",
        description = "깃허브의 레포지터리 목록 조회한다."
    )
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = GithubResponseItemDto.class))
            )
        }
    )
    public List<GithubResponseItemDto> getGitRepositories(
        @Parameter(name = "github_id", description = "깃허브 ID", in = ParameterIn.PATH)
        @PathVariable(value = "github_id") final String gitHubId
    ) {
        return profileService.getGitRepositories(gitHubId);
    }

    @PostMapping("/image")
    @Operation(
        summary = "프로필 이미지 업로드 API",
        description = "프로필 이미지를 업로드한다."
    )
    @ApiResponse(
        responseCode = "200"
    )
    public void saveProfileImage(@ModelAttribute(name = "file") MultipartFile file) {
        imageService.saveImage(file);
    }
}
