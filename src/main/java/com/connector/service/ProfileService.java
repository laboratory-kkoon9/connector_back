package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
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

    public List<ProfileDto> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDto> profileDtos = profiles.stream()
                .map(p -> new ProfileDto(
                        p.getUser(),
                        p.getExperiences().isEmpty() ? "" :  p.getExperiences().get(0).getCompany(),
                        p.getLocation(),
                        p.getBio(),
                        p.getSkills().stream().map(Skill::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return profileDtos;
    }
}
