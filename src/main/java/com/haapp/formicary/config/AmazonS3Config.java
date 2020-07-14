package com.haapp.formicary.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@AllArgsConstructor
@PropertySource("classpath:application.yaml")
public class AmazonS3Config {

    private final Environment environment;

    @Bean
    public AmazonS3 amazonS3Client() {

        environment.getProperty("");
        AWSCredentials credentials = new BasicAWSCredentials(
                Objects.requireNonNull(environment.getProperty("cloud.aws.credentials.accessKey")),
                Objects.requireNonNull(environment.getProperty("cloud.aws.credentials.secretKey")));

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(environment.getProperty("cloud.aws.s3.region")))
                .build();
    }
}
