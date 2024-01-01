package com.tech.bee.postservice.service;

import com.tech.bee.postservice.common.ErrorDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.dto.InterestDTO;
import com.tech.bee.postservice.entity.InterestEntity;
import com.tech.bee.postservice.entity.ProfileEntity;
import com.tech.bee.postservice.exception.BaseCustomException;
import com.tech.bee.postservice.mapper.InterestMapper;
import com.tech.bee.postservice.repository.InterestRepository;
import com.tech.bee.postservice.repository.ProfileRepository;
import com.tech.bee.postservice.util.AppUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final ProfileRepository profileRepository;
    private final InterestMapper interestMapper;
    private final InterestRepository interestRepository;
    private final SecurityService securityService;

    public void createInterests(final String profileId ,
                         final List<InterestDTO> interests){
        ProfileEntity existingProfileEntity = profileRepository.findByProfileIdentifier(profileId).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        List<ErrorDTO> ownershipErrors = securityService.validateOwnership(existingProfileEntity.getUserId());
        if(CollectionUtils.isNotEmpty(ownershipErrors))
            throw BaseCustomException.builder().errors(ownershipErrors).httpStatus(HttpStatus.FORBIDDEN).build();
        List<InterestEntity> interestEntities = mapToInterestEntity(interests);
        CollectionUtils.addAll(existingProfileEntity.getInterests() , interestEntities);
        interestRepository.saveAll(interestEntities);
    }

    public List<String> findInterests(@NonNull final String userId){
        ProfileEntity existingProfileEntity = profileRepository.findByUserId(userId).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        List<ErrorDTO> ownershipErrors = securityService.validateOwnership(existingProfileEntity.getUserId());
        if(CollectionUtils.isNotEmpty(ownershipErrors))
            throw BaseCustomException.builder().errors(ownershipErrors).httpStatus(HttpStatus.FORBIDDEN).build();
        Set<InterestEntity> interests = existingProfileEntity.getInterests();
        return interests.stream().map(InterestEntity::getTagIdentifier).collect(Collectors.toList());
    }



    private List<InterestEntity> mapToInterestEntity(List<InterestDTO> interests){
        return interests.stream().map(interestMapper::toEntity).collect(Collectors.toList());
    }
}
