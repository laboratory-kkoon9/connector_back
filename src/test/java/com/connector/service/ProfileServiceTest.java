package com.connector.service;

import com.connector.dto.ProfileDetailDto;
import com.connector.dto.ProfileDto;
import com.connector.global.exception.BadRequestException;
import com.connector.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
@Sql("classpath:/sql/profile.sql")
class ProfileServiceTest {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private PostRepository postRepository;

    @Mock
    private GithubClient githubClient;

    private ProfileService profileService;

    @BeforeEach
    void init() {
        profileService = new ProfileService(profileRepository, userRepository, experienceRepository, educationRepository, postRepository, githubClient);
    }

    @Test
    @DisplayName("전체 프로필 조회 정보를 리턴한다.")
    void get_profiles_test() {
        // given, when
        List<ProfileDto> result = profileService.getProfiles();

        // then
        ProfileDto profile = result.get(0);
        assertAll(
                () -> assertThat(profile.getLocation()).isEqualTo("Bucheon"),
                () -> assertThat(profile.getBio()).isEqualTo("안녕하세요 JSCode의 Eric 부캐입니다."),
                () -> assertAll(
                        () -> assertThat(profile.getSkills()).hasSize(4),
                        () -> assertThat(profile.getSkills().get(0)).isEqualTo("React"),
                        () -> assertThat(profile.getSkills().get(1)).isEqualTo("Express"),
                        () -> assertThat(profile.getSkills().get(2)).isEqualTo("Spring Framework"),
                        () -> assertThat(profile.getSkills().get(3)).isEqualTo("Android")
                )
        );
    }

    @Test
    @DisplayName("유효하지 않은 userId에 대해서는 BadRequestException 예외를 던진다.")
    void profile_detail_test1() {
        // given
        Long userId = 3L;

        // when, then
        assertThatThrownBy(() -> profileService.getOneProfile(userId))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Not User");
    }

    @Test
    @DisplayName("유효하지 않은 user에 대해서는 BadRequestException 예외를 던진다.")
    void profile_detail_test2() {
        // given
        Long userId = 2L;

        // when, then
        assertThatThrownBy(() -> profileService.getOneProfile(userId))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Not Profile");
    }

    @Test
    @DisplayName("프로필 상세 조회 정보를 리턴한다.")
    void profile_detail_test3() {
        // given
        Long userId = 1L;

        // when
        ProfileDetailDto result = profileService.getOneProfile(userId);

        // then
        assertAll(
                () -> assertThat(result.getBio()).isEqualTo("안녕하세요 JSCode의 Eric 부캐입니다."),
                () -> assertAll(
                        () -> assertThat(result.getExperience()).hasSize(1),
                        () -> assertThat(result.getExperience().get(0).getCompany()).isEqualTo("Google")
                ),
                () -> assertAll(
                        () -> assertThat(result.getEducation()).hasSize(2),
                        () -> assertThat(result.getEducation().get(0).getSchool()).isEqualTo("덕산중학교"),
                        () -> assertThat(result.getEducation().get(1).getSchool()).isEqualTo("덕산고등학교")
                ),
                () -> assertAll(
                        () -> assertThat(result.getSkills()).hasSize(4),
                        () -> assertThat(result.getSkills().get(0)).isEqualTo("React"),
                        () -> assertThat(result.getSkills().get(1)).isEqualTo("Express"),
                        () -> assertThat(result.getSkills().get(2)).isEqualTo("Spring Framework"),
                        () -> assertThat(result.getSkills().get(3)).isEqualTo("Android")
                ),
                () -> assertThat(result.getLocation()).isEqualTo("Bucheon")
        );
    }

    @Test
    @DisplayName("유효하지 않은 userId에 대해서는 BadRequestException 예외를 던진다.")
    void profile_upsert_test1() {
        // given
        Long userId = 3L;

        // when, then
        assertThatThrownBy(() -> profileService.upsertProfile(userId, null))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Not User");
    }


}