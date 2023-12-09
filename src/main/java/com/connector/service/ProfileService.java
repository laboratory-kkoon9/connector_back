package com.connector.service;

import com.connector.domain.*;
import com.connector.dto.EducationDto;
import com.connector.dto.ExperienceDto;
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
        List<ProfileDto> profileDtos = profiles.stream()
                .map(p -> new ProfileDto(
                        p.getUser(),
                        p.getExperiences().isEmpty() ? "" : p.getExperiences().get(0).getCompany(),
                        p.getLocation(),
                        p.getBio(),
                        p.getSkills().stream().map(Skill::getName).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return profileDtos;
    }

    public ProfileDto getProfileDetail(Long userId) {
        Profile profile = profileRepository.findById(userId).get();
        ProfileDto profileDto = new ProfileDto(
                profile.getUser(),
                profile.getExperiences().isEmpty() ? "" : profile.getExperiences().get(0).getCompany(),
                profile.getLocation(),
                profile.getBio(),
                profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList()),
                profile.getExperiences().stream().map(ex -> new ExperienceDto(
                        ex.getCompany(),
                        ex.getPosition(),
                        ex.getDescription(),
                        ex.getStartDate(),
                        ex.getEndDate()
                )).collect(Collectors.toList()),
                profile.getEducations().stream().map(ed -> new EducationDto(
                        ed.getSchool(),
                        ed.getDegree(),
                        ed.getFieldOfStudy(),
                        ed.getStartDate(),
                        ed.getEndDate()
                )).collect(Collectors.toList())
        );
        return profileDto;
    }

}
