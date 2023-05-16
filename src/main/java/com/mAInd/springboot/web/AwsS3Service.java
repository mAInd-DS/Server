package com.mAInd.springboot.web;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public void uploadObject(byte[] bytes, String storedFileName) throws IOException{
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentType("text/plain");
        omd.setContentLength(bytes.length);
        omd.setHeader("filename", storedFileName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName+"/"+date.format(new Date()), storedFileName, new ByteArrayInputStream(bytes), omd);
        amazonS3.putObject(putObjectRequest);
    }

    public void deleteObject(String date, String storedFileName) throws AmazonServiceException {
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName + "/" + date, storedFileName));

    }

}
