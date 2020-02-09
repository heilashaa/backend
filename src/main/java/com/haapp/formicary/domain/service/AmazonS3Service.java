package com.haapp.formicary.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AmazonS3Service {

    @Resource
    private AmazonS3 amazonS3;

    public List<S3ObjectSummary> getS3ObjectsInBucket(String bucket) {
         return getS3ObjectsInBucket(bucket, null);
    }

    public S3Object getS3ObjectInBucket(String bucket, String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
        return amazonS3.getObject(getObjectRequest);
    }

    public String getS3ObjectInBucketAsString(String bucket, String key) {
        return amazonS3.getObjectAsString(bucket, key);
    }

    public void uploadFileTos3bucket(String bucket, File file) {
        amazonS3.putObject(new PutObjectRequest(bucket, file.getName(), file));
    }

    public void uploadFileTos3bucket(PutObjectRequest request) {
        amazonS3.putObject(request);
    }

    public void uploadFileTos3bucket(String bucket, String name, File file) {
        amazonS3.putObject(new PutObjectRequest(bucket, name, file));
    }

    public void copy(String sourceName, String sourceBuscketSource,
                     String destinationName, String destinationBucket) {
        CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                sourceBuscketSource, sourceName,
                destinationBucket, destinationName);
        amazonS3.copyObject(copyObjRequest);
    }

    public List<S3ObjectSummary> getS3ObjectsInBucket(String bucket, String prefix) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withPrefix(prefix)
                .withBucketName(bucket);
        List<S3ObjectSummary> s3Objects = new ArrayList<>();

        ObjectListing objects = amazonS3.listObjects(listObjectsRequest);
        while (true) {
            List<S3ObjectSummary> summaries = objects.getObjectSummaries();
            if (summaries.size() < 1) {
                break;
            }
            for (S3ObjectSummary item : summaries) {
                if (!item.getKey().endsWith("/")) {
                    s3Objects.add(item);
                }
            }
            objects = amazonS3.listNextBatchOfObjects(objects);
        }
        return s3Objects;
    }
}
