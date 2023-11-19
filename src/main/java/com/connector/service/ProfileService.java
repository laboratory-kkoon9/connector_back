package com.connector.service;

import com.connector.domain.Education;
import com.connector.domain.Experience;
import com.connector.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private List<Profile> profiles = Arrays.asList(Profile.builder()
                    .id(1L)
                    .name("Nam Koong Kwon")
                    .avatar("https://avatars.githubusercontent.com/u/43670900?s=96&v=4")
                    .company("student at SK Hynix")
                    .location("Bucheon")
                    .skills(Arrays.asList("React", "Expess", "Spring Framework"))
                    .education(Arrays.asList(Education.builder()
                                    .school("덕산중학교")
                                    .degree(3)
                                    .fieldofstudy("공과대")
                                    .from(LocalDate.of(2020, 1, 1))
                                    .to(LocalDate.of(2023, 1, 1))
                                    .build(), Education.builder()
                                    .school("덕산고등학교")
                                    .degree(3)
                                    .fieldofstudy("이대")
                                    .from(LocalDate.of(2011, 1, 1))
                                    .to(LocalDate.of(2014, 2, 28))
                                    .build()
                            )
                    )
                    .experience(Arrays.asList(
                            Experience.builder()
                                    .company("SK")
                                    .position("Junior Developer")
                                    .description("JSK")
                                    .from(LocalDate.of(2011, 1, 1))
                                    .build()
                    ))
                    .build(),

            Profile.builder()
                    .id(2L)
                    .name("Choi Jin Min")
                    .avatar("")
                    .company("Developer at asdf")
                    .location("asdf")
                    .skills(Arrays.asList("Android"))
                    .education(Collections.emptyList())
                    .experience(Collections.emptyList())
                    .build()
    );

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Profile getProfileById(final Long userId) {
        return profiles.get(userId.intValue() - 1);
    }
}
