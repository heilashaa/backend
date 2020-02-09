package com.haapp.formicary.domain.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.haapp.formicary.config.AwsConfigProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
public class S3FileStorageFacade {

    private static final String SLASH = "/";

    private final AmazonS3Service provider;

    private final AwsConfigProperties awsConfigProperties;

    public void uploadTripImages(String tripId, List<MultipartFile> images) {
        images.forEach(image -> uploadTripImage(tripId, image));
    }

    private void uploadTripImage(String tripId, MultipartFile image) {
        try {
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(image.getContentType());
            data.setContentLength(image.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    awsConfigProperties.getS3().getTripImagesBucket(),
                    buildS3Key(tripId, image.getOriginalFilename()),
                    image.getInputStream(), data).withCannedAcl(CannedAccessControlList.PublicRead);

            provider.uploadFileTos3bucket(putObjectRequest);
        } catch (Exception e) {
            log.error("Error upload trip image", e);
        }
    }

    private String buildS3Key(String tripId, String fileName) {
        return tripId + SLASH + fileName;
    }


    public List<String> fetchTripImagesUrl(String tripId) {
        try {
            List<S3ObjectSummary> objectSummaries = provider.getS3ObjectsInBucket(
                    awsConfigProperties.getS3().getTripImagesBucket(), tripId);
            return objectSummaries.stream()
                                  .map(this::extractObjectUrl)
                                  .collect(toList());
        } catch (Exception e) {
            log.error("Error fetch trip images", e);
        }
        return emptyList();
    }

    private String extractObjectUrl(S3ObjectSummary objectSummary) {
        return awsConfigProperties.getS3().getObjectUrlPrefix()
                + objectSummary.getKey();
    }
}
