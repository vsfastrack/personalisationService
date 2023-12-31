package com.tech.bee.postservice.resources;

import com.tech.bee.postservice.annotation.RequestMetrics;
import com.tech.bee.postservice.annotation.TransactionId;
import com.tech.bee.postservice.common.ApiResponseDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.dto.ProfileDTO;
import com.tech.bee.postservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.PathConstants.PATH_PROFILE_RESOURCE)
public class ProfileResource {

    private final ProfileService profileService;

    @TransactionId
    @RequestMetrics
    @PostMapping
    public ResponseEntity<ApiResponseDTO> create(@RequestBody ProfileDTO profileDTO){
        String profileId = profileService.create(profileDTO);
        return new ResponseEntity<>(ApiResponseDTO.builder().content(profileId).build() , HttpStatus.CREATED);
    }

    @TransactionId
    @RequestMetrics
    @GetMapping
    public ResponseEntity<ApiResponseDTO> find(){
        return new ResponseEntity<>(ApiResponseDTO.builder().content(profileService.find()).build() ,HttpStatus.OK);
    }
}
