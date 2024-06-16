package com.connector.service;

import com.connector.domain.Profile;
import com.connector.domain.User;
import com.connector.dto.ProfileDetailDto;
import com.connector.global.exception.BadRequestException;
import com.connector.repository.EducationRepository;
import com.connector.repository.ExperienceRepository;
import com.connector.repository.ProfileRepository;
import com.connector.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProfileServiceMockTest {
    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void init() {
        profileService = new ProfileService(profileRepository, userRepository, experienceRepository, educationRepository, githubClient);
    }

    @Test
    @DisplayName("유효하지 않은 user에 대해서는 BadRequestException 예외를 던진다.")
    void profile_detail_test2() {
        // given
        Long userId = 1L;
        given(profileRepository.findByUserId(any())).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> profileService.getProfileById(userId))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Not Profile");
    }


    @Test
    @DisplayName("프로필 상세 조회 정보를 리턴한다.")
    void profile_detail_test3() {
        // given
        Long userId = 1L;
        User user = User.builder().id(userId).build();
        Profile profile = Profile.builder()
                .user(user)
                .bio("자기소개")
                .website("https://www.naver.com")
                .company("SK Hynix")
                .location("Bucheon")
                .skills(new ArrayList<>())
                .experiences(new ArrayList<>())
                .educations(new ArrayList<>())
                .build();
        given(profileRepository.findByUserId(any())).willReturn(Optional.of(profile));

        // when
        ProfileDetailDto result = profileService.getProfileById(userId);

        // then
        assertAll(
                () -> assertThat(result.getBio()).isEqualTo("자기소개"),
                () -> assertThat(result.getWebsite()).isEqualTo("https://www.naver.com"),
                () -> assertThat(result.getCompany()).isEqualTo("SK Hynix"),
                () -> assertThat(result.getLocation()).isEqualTo("Bucheon")
        );
    }
}
