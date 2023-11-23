package com.connector.service;

import com.connector.domain.Profile;
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

    public Profile getProfileById(final Long userId) {
        return profileRepository.findById(userId);
    }
}
