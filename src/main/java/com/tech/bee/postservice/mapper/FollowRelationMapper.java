package com.tech.bee.postservice.mapper;

import com.tech.bee.postservice.entity.FollowRelationEntity;
import com.tech.bee.postservice.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowRelationMapper {

    @Mapping(source = "follower" , target = "follower")
    @Mapping(source = "followed" , target = "followed")
    @Mapping(target = "createdWhen" , ignore = true)
    @Mapping(target = "lastModified" , ignore = true)
    FollowRelationEntity toEntity(final ProfileEntity follower,
                                  final ProfileEntity followed);
}