package com.tech.bee.postservice.repository;

import com.tech.bee.postservice.entity.FollowRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FollowRelationRepository extends JpaRepository<FollowRelationEntity,Long> {
}
