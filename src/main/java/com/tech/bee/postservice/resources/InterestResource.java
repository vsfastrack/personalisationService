package com.tech.bee.postservice.resources;

import com.tech.bee.postservice.annotation.RequestMetrics;
import com.tech.bee.postservice.annotation.TransactionId;
import com.tech.bee.postservice.common.ApiResponseDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.PathConstants.PATH_INTEREST_RESOURCE)
public class InterestResource {

    private final InterestService interestService;

    @TransactionId
    @RequestMetrics
    @PostMapping(value = "/{userId}")
    public ResponseEntity<ApiResponseDTO> fetchInterests(@PathVariable("userId") final String userId){
//        interestService.createInterests(userId);
        return new ResponseEntity<>(ApiResponseDTO.builder().build(), HttpStatus.NO_CONTENT);
    }
}
