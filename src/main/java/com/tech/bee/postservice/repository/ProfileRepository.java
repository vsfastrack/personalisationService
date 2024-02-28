package com.tech.bee.postservice.repository;

import com.tech.bee.postservice.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUserId(String userId);
    Optional<ProfileEntity> findByProfileIdentifier(String profileId);

    @Query(value = "SELECT p.profile_identifier FROM profile p LEFT JOIN follow_relation AS fr ON p.id = fr.followed_id WHERE p.id = :profileId",nativeQuery = true)
    List<String> findFollowers(final Long profileId);
}
