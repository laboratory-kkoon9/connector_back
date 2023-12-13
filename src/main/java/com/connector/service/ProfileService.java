package com.connector.service;

import com.connector.domain.Profile;
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

    public List<ProfileDto> findProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(ProfileDto::new).collect(Collectors.toList());
    }

    public ProfileDetailDto findOneProfile(Long userId) {
        Profile profile = profileRepository.findById(userId).get();
        return new ProfileDetailDto(profile);
    }
}
