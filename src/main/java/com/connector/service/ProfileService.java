package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.User;
import com.connector.dto.ExperienceDto;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.global.exception.BadRequestException;
import com.connector.repository.ExperienceRepository;
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
    private final ExperienceRepository experienceRepository;


    @Transactional(readOnly = true)
    public List<ProfileDto> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(ProfileDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfileDetailDto getOneProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
        );
        return new ProfileDetailDto(profile);
    }

    @Transactional
    public void addExperience(Long userId, ExperienceDto experienceDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
        );
        profile.addExperience(experienceDto.toEntity());
    }

}
