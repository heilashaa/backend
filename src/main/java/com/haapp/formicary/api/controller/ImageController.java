package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.ConfirmationResponse;
import com.haapp.formicary.domain.service.S3FileStorageFacade;
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
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
public class ImageController {

    private final S3FileStorageFacade fileStorageFacade;

    @ApiOperation(value = "${trip.service.operation.addPhoto}", nickname = "addPhoto")
    @PostMapping(path = "/{id}/photo", consumes = MULTIPART_FORM_DATA_VALUE)
    public ConfirmationResponse addPhoto(
            @ApiParam(value = "${trip.service.param.trip.id}", required = true)
            @PathVariable String id,
            @ApiParam(value = "${trip.service.param.image.array}", /*allowMultiple = true,*/ required = true)
            @RequestPart("images") MultipartFile/*[]*/ images) {
        fileStorageFacade.uploadTripImages(id, asList(images));
        return SUCCESS;
    }
}
