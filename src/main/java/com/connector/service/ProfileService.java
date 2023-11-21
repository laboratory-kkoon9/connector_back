package com.connector.service;

import com.connector.domain.Profile;
import com.connector.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileById(final Long userId) {
        return profileRepository.findById(userId);
    }
}
