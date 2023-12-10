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
        return profiles.stream().map(ProfileDto::new).collect(Collectors.toList());
    }

    public ProfileDetailDto getProfileDetail(Long userId) {
        Profile profile = profileRepository.findById(userId).get();
        return new ProfileDetailDto(profile);
    }
}
