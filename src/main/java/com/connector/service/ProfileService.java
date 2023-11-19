package com.connector.service;

import com.connector.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
                    .build(),

            Profile.builder()
                    .id(2L)
                    .name("Choi Jin Min")
                    .avatar("")
                    .company("Developer at asdf")
                    .location("asdf")
                    .skills(Arrays.asList("Android"))
                    .build()
    );

    public List<Profile> getProfiles() {
        return profiles;
    }
}
