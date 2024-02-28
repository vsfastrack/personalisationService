package com.tech.bee.postservice.resources;

import com.tech.bee.postservice.common.ApiResponseDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.service.S3DocServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.PathConstants.PATH_DOCUMENT)
public class ImageUploadResource {

    @Autowired
    private S3DocServices s3DocumetServices;

    @PatchMapping(value = ApiConstants.PathConstants.UPLOAD_DOCUMENT)
    public ResponseEntity<ApiResponseDTO> uploadDocument(@RequestBody File file) {
        return new ResponseEntity<>(ApiResponseDTO.builder().content(s3DocumetServices.uploadDocuments(file)).build(), HttpStatus.OK);
    }

    @GetMapping(value = ApiConstants.PathConstants.FETCH_DOCUMENT)
    public ResponseEntity<ApiResponseDTO> fetchDocument(@PathVariable("key") String key) {
        return new ResponseEntity<>(ApiResponseDTO.builder().content(s3DocumetServices.getDocuments(key)).build(), HttpStatus.OK);
    }

}
