package com.connector.service;

import com.connector.domain.*;
import com.connector.dto.EducationDto;
import com.connector.dto.ExperienceDto;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<ProfileDto> getProfileList() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDto> profileDtos = profiles.stream()
                .map(profile -> new ProfileDto(
                        profile.getUser(),
                        profile.getExperiences().isEmpty() ? "" : profile.getExperiences().get(0).getCompany(),
                        profile.getLocation(),
                        profile.getBio(),
                        profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return profileDtos;
    }

    public ProfileDetailDto getProfileDetail(Long userId) {
        Profile profile = profileRepository.findById(userId).get();
        ProfileDetailDto profileDetailDto = new ProfileDetailDto(
                profile.getUser(),
                profile.getExperiences().isEmpty() ? "" : profile.getExperiences().get(0).getCompany(),
                profile.getLocation(),
                profile.getBio(),
                profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList()),
                profile.getExperiences().stream().map(ExperienceDto::new).collect(Collectors.toList()),
                profile.getEducations().stream().map(EducationDto::new).collect(Collectors.toList())
        );
        return profileDetailDto;
    }
}
