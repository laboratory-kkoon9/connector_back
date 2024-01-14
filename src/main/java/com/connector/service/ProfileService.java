package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import com.connector.dto.ProfileDto;
import com.connector.dto.UserDto;
import com.connector.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProfileDto convertToDto(Profile profile) { // convertToDto 메소드는 Profile 엔티티를 입력으로 받아, 이를 ProfileDto 객체로 변환
        UserDto userDto = UserDto.builder()
                .id(profile.getUser().getId())
                .name(profile.getUser().getName())
                .email(profile.getUser().getEmail())
                .avatar(profile.getUser().getAvatar())
                .build();

        List<String> skillNames = profile.getSkills().stream()
                .map(Skill::getName)
                .collect(Collectors.toList()); // 스트림의 map 연산을 사용하여 각 Skill 객체의 getName 메소드를 호출하고, 결과를 새 리스트로 수집

        return ProfileDto.builder()
                .user(userDto)
                .company(profile.getCompany())
                .location(profile.getLocation())
                .bio(profile.getBio())
                .skills(skillNames) // 추출된 스킬 이름의 리스트를 ProfileDto의 skills 필드에 할당
                .build();
    }
}