package com.tech.bee.postservice.repository;

import com.tech.bee.postservice.entity.InterestEntity;
import com.tech.bee.postservice.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
}
