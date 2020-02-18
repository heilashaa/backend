package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.ConfirmationResponse;
import com.haapp.formicary.domain.service.FileUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.haapp.formicary.api.message.ConfirmationResponse.SUCCESS;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
public class ImageController {

    private final FileUploadService fileUploadService;

    @ApiOperation(value = "add image")
    @PostMapping(path = "/{id}/image", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ConfirmationResponse addImage(
            @ApiParam(value = "s3 bucket folder", required = true)
            @PathVariable String id,
            @ApiParam(value = "image", /*allowMultiple = true,*/ required = true)
            @RequestPart("images") MultipartFile/*[]*/ images) {
        fileUploadService.uploadImages(id, asList(images));
        System.out.println(fileUploadService.getImageUrl().toString());
        return SUCCESS;
    }
}
