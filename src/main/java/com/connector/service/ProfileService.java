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

    @Transactional(readOnly = true)
    public List<ProfileDto> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();
        for (int i = 0; i < profiles.size(); i++) {
            Profile profile = profiles.get(i);
            profileDtos.add(ProfileDto.builder()
                    .user(profile.getUser())
                    .bio(profile.getBio())
                    .company(profile.getExperiences().isEmpty() ? "" : profile.getExperiences().get(0).getCompany())
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
                .company(profile.getExperiences().get(0).getCompany())
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

        // update
        if(optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.update(profileDto);

            // 기존에 있던 skill 지우기
            if(profileDto.getSkills() != null) {
                List<String> skillNames = TextParser.doSplitCode(profileDto.getSkills());
                List<Skill> skills = skillNames.stream().map(
                        Skill::of
                ).collect(Collectors.toList());
                profile.addSkills(skills);
            }

        } else {

        }
    }
}
