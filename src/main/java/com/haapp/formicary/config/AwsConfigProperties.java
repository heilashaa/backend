package com.haapp.formicary.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cloud.aws")
public class AwsConfigProperties {

    @Value("${cloud.aws.region.static}")
    private String region;
    private Credentials credentials;
    private S3 s3;

    @Getter
    @Setter
    public static class Credentials {
        private String accessKey;
        private String secretKey;
    }

    @Getter
    @Setter
    public static class S3 {
        private String firmwareStorageBucket;
        private String firmwareBucket;
        private String penaltyImagesBucket;
        private String tripImagesBucket;
        private String publicOfferBucket;
        private String objectUrlPrefix;
        private String region;
    }
}
