package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public List<ProfileDto> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();
        for (int i = 0; i < profiles.size(); i++) {
            Profile profile = profiles.get(i);
            profileDtos.add(ProfileDto.builder()
                    .user(profile.getUser())
                    .bio(profile.getBio())
                    .company(profile.getCompany())
                    .location(profile.getLocation())
                    .skills(profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList()))
                    .build());
        }
        return profileDtos;
    }

    @Transactional(readOnly = true)
    public ProfileDetailDto getProfileById(final Long userId) {
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("Not Profile")
        );

        ProfileDetailDto profileDetailDto = ProfileDetailDto.builder()
                .user(profile.getUser())
                .bio(profile.getBio())
                .company(profile.getCompany())
                .website(profile.getWebsite())
                .location(profile.getLocation())
                .skills(profile.getSkills())
                .educations(profile.getEducations())
                .experiences(profile.getExperiences())
                .build();
        return profileDetailDto;
    }
}
