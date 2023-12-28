package com.connector.repository;

import com.connector.domain.Profile;
import com.connector.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
}