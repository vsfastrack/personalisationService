package com.tech.bee.postservice.service;

import com.tech.bee.postservice.common.ErrorDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.dto.ProfileDTO;
import com.tech.bee.postservice.dto.StatsDTO;
import com.tech.bee.postservice.entity.ProfileEntity;
import com.tech.bee.postservice.exception.BaseCustomException;
import com.tech.bee.postservice.mapper.ProfileMapper;
import com.tech.bee.postservice.mapper.StatsMapper;
import com.tech.bee.postservice.repository.ProfileRepository;
import com.tech.bee.postservice.util.AppUtil;
import com.tech.bee.postservice.validator.ProfileValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    public String create(ProfileDTO profileDTO){
        List<ErrorDTO> validationErrors = profileValidator.validate(profileDTO);
        if(CollectionUtils.isNotEmpty(validationErrors))
            throw BaseCustomException.builder().errors(validationErrors).httpStatus(HttpStatus.BAD_REQUEST).build();
        ProfileEntity profileEntity = profileMapper.toEntity(profileDTO , securityService.getCurrentLoggedInUser());
        profileRepository.save(profileEntity);
        return profileEntity.getProfileIdentifier();
    }

    public ProfileDTO find(){
        ProfileEntity profileEntity = profileRepository.findByUserId(securityService.getCurrentLoggedInUser()).orElseThrow(() -> BaseCustomException.builder().
                errors(Collections.singletonList(AppUtil.buildResourceNotFoundError(ApiConstants.KeyConstants.KEY_PROFILE))).httpStatus(HttpStatus.NOT_FOUND)
                .build());
        return profileMapper.toDTO(profileEntity , extractStatistics(profileEntity));
    }

    private StatsDTO extractStatistics(ProfileEntity profileEntity){
        if(Objects.nonNull(profileEntity.getStatistics()))
            return statsMapper.toDTO(profileEntity.getStatistics());
        return null;
    }

}
