package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.ConfirmationResponse;
import com.haapp.formicary.api.message.ImageResponse;
import com.haapp.formicary.api.message.ImagesResponse;
import com.haapp.formicary.api.model.ImageApi;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.haapp.formicary.api.message.ConfirmationResponse.SUCCESS;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for images.")
@RestController
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class ImageController {

    private final ModelMapper modelMapper;
    private final ImageService imageService;

    @ApiOperation(value = "Add image to campaign", nickname = "addImageToCampaign")
    @PostMapping(value = "/users/{userId}/campaigns/{campaignId}/images")
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public ImageResponse addImageToCampaign(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @RequestPart MultipartFile file) {
        var image = imageService.addImageToCampaign(campaignId, file);
        var apiImage = modelMapper.map(image, ImageApi.class);
        return new ImageResponse(apiImage);
    }
    @ApiOperation(value = "Get campaign images", nickname = "getCampaignImages")
    @GetMapping(value = "/campaigns/{campaignId}/images")
    @ResponseStatus(CREATED)
    public ImagesResponse getCampaignImages(
            @PathVariable Long campaignId) {
        var image = imageService.findImagesByCampaignId(campaignId);
        List<ImageApi> apiImages =  asList(modelMapper.map(image, ImageApi[].class));
        return new ImagesResponse(apiImages);
    }

    @ApiOperation(value = "Delete campaign image", nickname = "deleteCampaignImage")
    @DeleteMapping(value = "/users/{userId}/campaigns/{campaignId}/images/{imageId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public ConfirmationResponse deleteCampaignImage(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @PathVariable Long imageId) {
        imageService.deleteCampaignImage(imageId);
        return SUCCESS;
    }
}
