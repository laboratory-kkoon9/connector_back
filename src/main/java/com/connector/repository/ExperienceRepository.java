package com.connector.repository;

import com.connector.domain.Experience;
import com.connector.domain.Profile;
import com.connector.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long>  {

}
