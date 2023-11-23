package com.connector.service;

import com.connector.domain.Profile;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

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
                    .skills(profile.getSkills())
                    .build());
        }
        return profileDtos;
    }

    public ProfileDetailDto getProfileById(final Long userId) {
        Profile profile = profileRepository.findById(userId);
        ProfileDetailDto profileDetailDto = ProfileDetailDto.builder()
                .user(profile.getUser())
                .bio(profile.getBio())
                .company(profile.getCompany())
                .location(profile.getLocation())
                .skills(profile.getSkills())
                .education(profile.getEducation())
                .experience(profile.getExperience())
                .build();
        return profileDetailDto;
    }
}
