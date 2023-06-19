package com.mAInd.springboot.web;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.transcribe.AmazonTranscribeClient;
import com.amazonaws.services.transcribe.AmazonTranscribeClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AmazonTranscribeConfig {
    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonTranscribeClient amazonTranscribeClient() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonTranscribeClient) AmazonTranscribeClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
//        return (AmazonTranscribeClient) AmazonTranscribeClient.builder()
//                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
//                .build();
    }
}
