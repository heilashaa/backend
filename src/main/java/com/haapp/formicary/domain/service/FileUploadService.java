package com.haapp.formicary.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.haapp.formicary.infrastructure.exception.ImageUploadException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.IMAGE_NOT_UPLOAD;

@Service
@Getter
@Setter
public class FileUploadService {

    private final AmazonS3 amazonS3Client;

    private String s3Key;

    private URL imageUrl;

    @Value("${cloud.aws.s3.imagesBucket}")
    private String imagesBucket;

    public FileUploadService(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    private void uploadImage(String s3Folder, MultipartFile image) {
        try {
            setS3Key(s3Folder, image.getOriginalFilename());
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    getImagesBucket(),
                    getS3Key(),
                    image.getInputStream(),
                    getMetadata(image))
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            uploadFileTos3bucket(putObjectRequest);
            URL uploadImageUrl = amazonS3Client.getUrl(getImagesBucket(), getS3Key());
            setImageUrl(uploadImageUrl);
        } catch (IOException e) {
            throw new ImageUploadException(IMAGE_NOT_UPLOAD);
        }
    }

    public void uploadImages(String s3Folder, List<MultipartFile> images) {
        images.forEach(image -> uploadImage(s3Folder, image));
    }

    private ObjectMetadata getMetadata(MultipartFile image){
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(image.getContentType());
        data.setContentLength(image.getSize());
        return data;
    }

    private void uploadFileTos3bucket(PutObjectRequest request) {
        amazonS3Client.putObject(request);
    }

    private void setS3Key(String s3Folder, String fileName) {
        this.s3Key = s3Folder + "/" + generateImageNamePrefix() + "-" + fileName;
    }

    private String getS3Key(){
        return s3Key;
    }

    private String generateImageNamePrefix(){
        return String.valueOf(new Random().nextInt(100));
    }
}
