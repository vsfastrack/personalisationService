package com.tech.bee.postservice.resources;

import com.tech.bee.postservice.annotation.RequestMetrics;
import com.tech.bee.postservice.annotation.TransactionId;
import com.tech.bee.postservice.common.ApiResponseDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.service.S3DocServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.PathConstants.PATH_DOCUMENT)
public class ImageUploadResource {

    @Autowired
    private S3DocServices s3DocumetServices;

    @PatchMapping(value = ApiConstants.PathConstants.UPLOAD_DOCUMENT)
    @TransactionId
    @RequestMetrics
    public ResponseEntity<ApiResponseDTO> uploadDocument(@RequestParam(value = "file", required = true) MultipartFile file) {
        return new ResponseEntity<>(ApiResponseDTO.builder().content(s3DocumetServices.uploadDouments(file)).build(), HttpStatus.OK);
    }

    @GetMapping(value = ApiConstants.PathConstants.FETCH_DOCUMENT)
    @TransactionId
    @RequestMetrics
    public ResponseEntity<ApiResponseDTO> fetchDocumentByKey(@PathVariable("key") String key) {
        return new ResponseEntity<>(ApiResponseDTO.builder().content(s3DocumetServices.getDocuments(key)).build(), HttpStatus.OK);
    }

}
