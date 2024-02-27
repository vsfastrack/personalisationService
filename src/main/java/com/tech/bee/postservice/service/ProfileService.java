package com.tech.bee.postservice.service;

import com.tech.bee.postservice.common.ErrorDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.dto.ProfileDTO;
import com.tech.bee.postservice.dto.StatsDTO;
import com.tech.bee.postservice.entity.FollowRelationEntity;
import com.tech.bee.postservice.entity.ProfileEntity;
import com.tech.bee.postservice.exception.BaseCustomException;
import com.tech.bee.postservice.mapper.FollowRelationMapper;
import com.tech.bee.postservice.mapper.ProfileMapper;
import com.tech.bee.postservice.mapper.StatsMapper;
import com.tech.bee.postservice.repository.FollowRelationRepository;
import com.tech.bee.postservice.repository.ProfileRepository;
import com.tech.bee.postservice.util.AppUtil;
import com.tech.bee.postservice.validator.ProfileValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;
    private final ProfileMapper profileMapper;
    private final SecurityService securityService;
    private final StatsMapper statsMapper;
    private final FollowRelationMapper followRelationMapper;
    private final FollowRelationRepository followRelationRepository;

    public String create(ProfileDTO profileDTO){
        List<ErrorDTO> validationErrors = profileValidator.validate(profileDTO);
        if(CollectionUtils.isNotEmpty(validationErrors))
            throw BaseCustomException.builder().errors(validationErrors).httpStatus(HttpStatus.BAD_REQUEST).build();
        ProfileEntity profileEntity = profileMapper.toEntity(profileDTO , securityService.getCurrentLoggedInUser());
        profileRepository.save(profileEntity);
        return profileEntity.getProfileIdentifier();
    }

    @Transactional
    public void edit(ProfileDTO profileDTO){
        List<ErrorDTO> validationErrors = profileValidator.validate(profileDTO);
        if(CollectionUtils.isNotEmpty(validationErrors))
            throw BaseCustomException.builder().errors(validationErrors).httpStatus(HttpStatus.BAD_REQUEST).build();
        ProfileEntity existingProfile =profileRepository.findByUserId(securityService.getCurrentLoggedInUser()).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        existingProfile.setEmail(profileDTO.getEmail());
        existingProfile.setFirstName(profileDTO.getFirstName());
        existingProfile.setLastName(profileDTO.getLastName());
    }

    public ProfileDTO find(){
        ProfileEntity profileEntity = profileRepository.findByUserId(securityService.getCurrentLoggedInUser()).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        List<String> followers = profileRepository.findFollowers(profileEntity.getId());
        ProfileDTO profileDTO = profileMapper.toDTO(profileEntity,followers);
        StatsDTO statsDTO = extractStatistics(profileEntity);
        profileDTO.setStatsDTO(statsDTO);
        return profileDTO;
    }

    @Transactional
    public void follow(final String followedProfileId){
        ProfileEntity followedProfile = profileRepository.findByProfileIdentifier(followedProfileId).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        final String currentUserId = securityService.getCurrentLoggedInUser();
        ProfileEntity followerProfile = profileRepository.findByUserId(currentUserId).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        FollowRelationEntity followRelation = followRelationMapper.toEntity(followerProfile,followedProfile);
        followRelationRepository.save(followRelation);
    }

    private StatsDTO extractStatistics(ProfileEntity profileEntity){
        if(Objects.nonNull(profileEntity.getStatistics()))
            return statsMapper.toDTO(profileEntity.getStatistics());
        return null;
    }

}
