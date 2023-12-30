package com.tech.bee.postservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3DocServices {

    private static final String BUCKET_URL = "https://{0}.s3.amazonaws.com/{1}";
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Autowired
    private AmazonS3 amazonS3Client;

    public S3Object getDocuments(String key) {
        // Get an object from S3
        return amazonS3Client.getObject(new GetObjectRequest(bucketName, key));
    }

    public String uploadDouments(File file) {
        String key = file.getName() + RandomUtils.nextInt();
        amazonS3Client.putObject(bucketName, key, file);
        return StringUtils.replace(BUCKET_URL, bucketName, key);
    }

}
