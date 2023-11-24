package com.connector.repository;

import com.connector.domain.Education;
import com.connector.domain.Experience;
import com.connector.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
