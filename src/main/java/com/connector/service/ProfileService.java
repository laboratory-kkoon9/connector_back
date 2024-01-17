package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import com.connector.domain.User;
import com.connector.dto.*;
import com.connector.global.exception.BadRequestException;
import com.connector.global.util.TextParser;
import com.connector.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final PostRepository postRepository;
    private final GithubClient githubClient;


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
    public void upsertProfile(Long userId, UpsertProfileDto profileDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Optional<Profile> optionalProfile = profileRepository.findByUser(user);

        if(optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.update(profileDto);

            if(profileDto.getSkills() != null) {
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
        List<Skill> skills = skillNames.stream().map(Skill::of).collect(Collectors.toList());
        profile.changeSkills(skills);
    }

    @Transactional
    public void deleteProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        profileRepository.deleteAllByUser(user);
        postRepository.deleteAllByUser(user);
        userRepository.delete(user);
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

    @Transactional
    public void deleteExperience(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }

    @Transactional
    public void addEducation(Long userId, EducationDto educationDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
        );
        profile.addEducation(educationDto.toEntity());
    }

    @Transactional
    public void deleteEducation(Long educationId) {
        educationRepository.deleteById(educationId);
    }

    @Transactional(readOnly = true)
    public List<GithubResponseItemDto> getGitRepositories(String gitHubId) {
        return githubClient.getUserRepositories(gitHubId);
    }
}
