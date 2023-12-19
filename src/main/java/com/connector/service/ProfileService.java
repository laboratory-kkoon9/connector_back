package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import com.connector.domain.User;
import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.dto.UpsertProfileDto;
import com.connector.global.exception.BadRequestException;
import com.connector.global.util.TextParser;
import com.connector.repository.ProfileRepository;
import com.connector.repository.SkillRepository;
import com.connector.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
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

    @Transactional
    public void upsertProfile(Long userId, UpsertProfileDto profileDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Optional<Profile> optionalProfile = profileRepository.findByUser(user);

        if(optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.update(profileDto);

            if(profileDto.getSkills() != null) {
                // skillRepository.deleteAllByProfile(profile);
                changeSkills(profileDto, profile);
            }
        } else {
            Profile profile = profileDto.toEntity(user);
            changeSkills(profileDto, profile);
            profileRepository.save(profile);
        }
    }

    private void changeSkills(UpsertProfileDto profileDto, Profile profile) {
        List<String> skillNames = TextParser.doSplitCode(profileDto.getSkills());
        List<Skill> skills = skillNames.stream().map(
                Skill::of
        ).collect(Collectors.toList());
        profile.changeSkills(skills);
    }
}
