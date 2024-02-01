package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import com.connector.domain.User;
import com.connector.dto.*;
import com.connector.global.exception.NotProfileException;
import com.connector.global.exception.NotUserException;
import com.connector.repository.ProfileRepository;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream()
                .map(this::convertToProfileDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfileDetailDto getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotUserException::new);

        Profile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(NotProfileException::new);

        return convertToProfileDetailDto(profile);
    }

    private ProfileDto convertToProfileDto(Profile profile) {
        return ProfileDto.builder()
                .user(convertToUserDto(profile.getUser()))
                .location(profile.getLocation())
                .company(profile.getCompany())
                .bio(profile.getBio())
                .skills(profile.getSkills().stream()
                        .map(Skill::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    private ProfileDetailDto convertToProfileDetailDto(Profile profile) {
        return ProfileDetailDto.builder()
                .user(convertToUserDto(profile.getUser()))
                .bio(profile.getBio())
                .company(profile.getCompany())
                .location(profile.getLocation())
                .skills(profile.getSkills().stream()
                        .map(Skill::getName)
                        .collect(Collectors.toList()))
                .experiences(extractExperienceDetails(profile))
                .educations(extractEducationDetails(profile))
                .build();
    }

    private UserDto convertToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .build();
    }

    private List<ExperienceDto> extractExperienceDetails(Profile profile) {
        return profile.getExperiences().stream()
                .map(experience -> ExperienceDto.builder()
                        .company(experience.getCompany())
                        .position(experience.getPosition())
                        .description(experience.getDescription())
                        .startDate(experience.getStartDate())
                        .endDate(experience.getEndDate())
                        .build())
                .collect(Collectors.toList());
    }

    private List<EducationDto> extractEducationDetails(Profile profile) {
        return profile.getEducations().stream()
                .map(education -> EducationDto.builder()
                        .school(education.getSchool())
                        .degree(education.getDegree())
                        .fieldOfStudy(education.getFieldOfStudy())
                        .startDate(education.getStartDate())
                        .endDate(education.getEndDate())
                        .build())
                .collect(Collectors.toList());
    }
}
