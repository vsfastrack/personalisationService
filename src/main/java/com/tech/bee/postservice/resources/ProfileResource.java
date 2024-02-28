package com.tech.bee.postservice.resources;

import com.tech.bee.postservice.annotation.RequestMetrics;
import com.tech.bee.postservice.annotation.TransactionId;
import com.tech.bee.postservice.common.ApiResponseDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.dto.InterestDTO;
import com.tech.bee.postservice.dto.ProfileDTO;
import com.tech.bee.postservice.service.InterestService;
import com.tech.bee.postservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.PathConstants.PATH_PROFILE_RESOURCE)
public class ProfileResource {

    private final ProfileService profileService;
    private final InterestService interestService;

    @TransactionId
    @RequestMetrics
    @PostMapping
    public ResponseEntity<ApiResponseDTO> create(@RequestBody ProfileDTO profileDTO){
        String profileId = profileService.create(profileDTO);
        return new ResponseEntity<>(ApiResponseDTO.builder().content(profileId).build() , HttpStatus.CREATED);
    }

    @TransactionId
    @RequestMetrics
    @PatchMapping
    public ResponseEntity<ApiResponseDTO> edit(@RequestBody ProfileDTO profileDTO){
        profileService.edit(profileDTO);
        return new ResponseEntity<>(ApiResponseDTO.builder().build() , HttpStatus.NO_CONTENT);
    }

    @TransactionId
    @RequestMetrics
    @GetMapping
    public ResponseEntity<ApiResponseDTO> find(){
        return new ResponseEntity<>(ApiResponseDTO.builder().content(profileService.find()).build() ,HttpStatus.OK);
    }

    @TransactionId
    @RequestMetrics
    @PostMapping(value = "/{profileId}/interests")
    public ResponseEntity<ApiResponseDTO> saveInterests(@PathVariable("profileId") final String profileId ,
                                                        @RequestBody List<InterestDTO> interests){
        interestService.createInterests(profileId , interests);
        return new ResponseEntity<>(ApiResponseDTO.builder().build(), HttpStatus.NO_CONTENT);
    }

    @TransactionId
    @RequestMetrics
    @PostMapping(value = "/{profileId}/follow")
    public ResponseEntity<ApiResponseDTO> follow(@PathVariable("profileId") final String profileId){
        profileService.follow(profileId);
        return new ResponseEntity<>(ApiResponseDTO.builder().build(), HttpStatus.NO_CONTENT);
    }
}
