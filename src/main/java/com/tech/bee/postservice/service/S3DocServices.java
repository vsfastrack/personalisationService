package com.tech.bee.postservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.tech.bee.postservice.common.ErrorDTO;
import com.tech.bee.postservice.exception.BaseCustomException;
import com.tech.bee.postservice.validator.ProfileValidator;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3DocServices {

    private static final String BUCKET_URL = "https://%s.s3.amazonaws.com/%s";
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Autowired
    private AmazonS3 amazonS3Client;
    @Autowired
    private ProfileValidator profileValidator;


    public File getDocuments(String key) {
        return convertInputStreamToFile(key);
    }

    public File convertInputStreamToFile(String key) {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + key);
        try (InputStream inputStream = amazonS3Client.getObject(new GetObjectRequest(this.bucketName, key)).getObjectContent(); FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public String uploadDouments(MultipartFile multiFile) {
        List<ErrorDTO> notificationErrors = new ArrayList<>();
        profileValidator.validateFileFormat(multiFile.getOriginalFilename(), notificationErrors);
        if (!CollectionUtils.isEmpty(notificationErrors)) {
            throw BaseCustomException.builder().errors(notificationErrors).httpStatus(HttpStatus.BAD_REQUEST).build();
        }
        String[] names = multiFile.getOriginalFilename() != null ? multiFile.getOriginalFilename().split("\\.") : new String[0];
        String fileNameWithKeys = names[0] + "_" + RandomUtils.nextInt() + "." + names[1];
        try {
            amazonS3Client.putObject(bucketName, fileNameWithKeys, multipartToFile(multiFile, multiFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format(BUCKET_URL, bucketName, fileNameWithKeys);
    }

    public static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

}
