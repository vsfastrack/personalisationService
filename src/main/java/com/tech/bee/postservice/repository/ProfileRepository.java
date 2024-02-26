package com.tech.bee.postservice.repository;

import com.tech.bee.postservice.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUserId(String userId);
    Optional<ProfileEntity> findByProfileIdentifier(String profileId);
}
