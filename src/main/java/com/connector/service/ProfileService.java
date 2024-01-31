package com.connector.service;

import com.connector.domain.Experience;
import com.connector.domain.Profile;
import com.connector.domain.Skill;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;


    // 프로필 전체 조회 메소드
    public List<ProfileDto> profile() {

        // 유저 전체 데이터 불러오기
        List<Profile> profiles = profileRepository.findAll();
        // 저장 공간
        List<ProfileDto> profileDtos = new ArrayList<>();
        for (int i = 0; i < profiles.size(); i++) {
            // 유저 쪼개기
            Profile profile = profiles.get(i);
            // 유저 개별 저장
            profileDtos.add(ProfileDto
                    .builder()
                    .user(profile.getUser())
                    .bio(profile.getBio())
                    .company(profile.getExperiences().isEmpty() ? "" : profile.getExperiences().get(0).getCompany())
                    .location(profile.getLocation())
                    .skills(profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList()))
                    .build());
        }
        return profileDtos;
    }

    // 프로필 상세조회
    public ProfileDetailDto profileDetail(Long userId) {

        /* 이러한 부분들이 뭔가 했는데 다 익셉션 이군요! */
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
        );


        ProfileDetailDto profileDetailDto;
        profileDetailDto = ProfileDetailDto.builder()
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

    // 프로필 상세조회
    public ProfileDetailDto profileDetailMe(Long userId) {

        /* 이러한 부분들이 뭔가 했는데 다 익셉션 이군요! */
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
        );


        ProfileDetailDto profileDetailDto;
        profileDetailDto = ProfileDetailDto.builder()
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


    public void profileExperience(Long userId, ExperienceDto experienceDto) {

        /* 로그인 및 프로필 익셉션 체크 */
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BadRequestException("Not User")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new BadRequestException("Not Profile")
        );

        profile.addExperience(experienceDto.toEntity());
    }




}
