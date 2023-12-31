package com.tech.bee.postservice.mapper;

import com.tech.bee.postservice.dto.StatsDTO;
import com.tech.bee.postservice.entity.StatsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatsMapper {

    @Mapping(source = "likesCount" , target = "likesCount")
    @Mapping(source = "followersCount" , target = "followersCount")
    @Mapping(source = "followingCount" , target = "followingCount")
    @Mapping(source = "commentsCount" , target = "commentsCount")
    @Mapping(source = "sharesCount" , target = "sharesCount")
    StatsDTO toDTO(final StatsEntity statsEntity);
}
