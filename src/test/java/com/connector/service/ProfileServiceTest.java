package com.connector.service;

import com.connector.dto.ProfileDetailDto;
import com.connector.global.config.JpaAuditConfig;
import com.connector.global.config.QuerydslConfig;
import com.connector.global.exception.BadRequestException;
import com.connector.repository.EducationRepository;
import com.connector.repository.ExperienceRepository;
import com.connector.repository.ProfileRepository;
import com.connector.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
@Import({QuerydslConfig.class, JpaAuditConfig.class})
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

    @Mock
    private GithubClient githubClient;

    private ProfileService profileService;

    @BeforeEach
    void init() {
        profileService = new ProfileService(profileRepository, userRepository, experienceRepository, educationRepository, githubClient);
    }

    @Test
    @DisplayName("유효하지 않은 user에 대해서는 BadRequestException 예외를 던진다.")
    void profile_detail_test2() {
        // given
        Long userId = 2L;

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

        // when
        ProfileDetailDto result = profileService.getProfileById(userId);

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
}
