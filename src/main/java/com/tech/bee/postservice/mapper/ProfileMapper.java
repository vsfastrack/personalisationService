package com.tech.bee.postservice.mapper;

import com.tech.bee.postservice.dto.ProfileDTO;
import com.tech.bee.postservice.dto.StatsDTO;
import com.tech.bee.postservice.entity.InterestEntity;
import com.tech.bee.postservice.entity.ProfileEntity;
import com.tech.bee.postservice.util.AppUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {

    @Mapping(source = "profileDTO.firstName" , target = "firstName")
    @Mapping(source = "profileDTO.lastName" , target = "lastName")
    @Mapping(source = "profileDTO.email" , target = "email")
    @Mapping(source = "userId" , target = "userId")
    @Mapping(target = "interests", ignore = true)
    @Mapping(target = "statistics", ignore = true)
    @Mapping( expression = "java(toDateOfBirth(profileDTO))" , target = "dateOfBirth")
    ProfileEntity toEntity(final ProfileDTO profileDTO,final String userId);

    @Mapping(source = "profileEntity.firstName" , target = "firstName")
    @Mapping(source = "profileEntity.lastName" , target = "lastName")
    @Mapping(source = "profileEntity.email" , target = "email")
    @Mapping(source = "profileEntity.fullName" , target = "fullName")
    @Mapping(source = "profileEntity.profilePicPath" , target = "profilePicPath")
    @Mapping( expression = "java(toInterests(profileEntity))" , target = "interests")
    @Mapping( source = "followers" , target = "followers")
    ProfileDTO toDTO(final ProfileEntity profileEntity , final List<String> followers);

    default LocalDate toDateOfBirth(ProfileDTO profileDTO){
        return AppUtil.convertStringToLocalDate(profileDTO.getDateOfBirth());
    }

    default List<String> toInterests(ProfileEntity profile){
        return AppUtil.extractInterests(profile.getInterests());
    }

    default String toFullName(final String firstName,final String lastName){
        return AppUtil.convertToFullName(firstName,lastName);
    }
}
