package com.tech.bee.postservice.mapper;

import com.tech.bee.postservice.dto.InterestDTO;
import com.tech.bee.postservice.entity.InterestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InterestMapper {

    @Mapping(source = "tagIdentifier" , target = "tagIdentifier")
    @Mapping(source = "name" , target = "name")
    InterestEntity toEntity(final InterestDTO interestDTO);
}
