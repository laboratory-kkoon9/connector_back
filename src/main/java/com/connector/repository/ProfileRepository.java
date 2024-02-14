package com.connector.repository;

import com.connector.domain.Profile;
import com.connector.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {   // extends JpaRepository<엔티티, pk타입>
    Optional<Profile> findByUser(User user);

}
