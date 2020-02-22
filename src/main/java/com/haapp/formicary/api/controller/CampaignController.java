package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.*;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.CampaignDto;
import com.haapp.formicary.domain.model.RatingDto;
import com.haapp.formicary.domain.service.CampaignService;
import com.haapp.formicary.domain.service.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for campaigns.")
@RestController
@RequestMapping(value = "/api/v1/campaigns", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class CampaignController {

    private ModelMapper modelMapper;
    private CampaignService campaignService;
    private RatingService ratingService;

    @ApiOperation(value = "Get article", nickname = "getCampaign")
    @GetMapping(value = "{campaignId}")
    @ResponseStatus(OK)
    public CampaignResponse getCampaign(@PathVariable Long campaignId) {
        var campaign = campaignService.findByIdRequired(campaignId);
        var apiCampain = modelMapper.map(campaign, com.haapp.formicary.api.model.Campaign.class);
        return new CampaignResponse(apiCampain);
    }

    @ApiOperation(value = "Add new campaign", nickname = "createCampaign")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public CampaignResponse createCampaign(
            @ApiParam(value = "campaign")
            @RequestBody @Valid CampaignRequest request) {
        var campaign = modelMapper.map(request.getCampaign(), CampaignDto.class);
        campaign = campaignService.create(campaign);
        var apiCampaign = modelMapper.map(campaign, com.haapp.formicary.api.model.Campaign.class);
        return new CampaignResponse(apiCampaign);
    }

    @ApiOperation(value = "Update campaign", nickname = "updateCampaign")
    @PutMapping(value = "/{campaignId}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public CampaignResponse updateCampaign(
            @PathVariable Long campaignId,
            @ApiParam(value = "campaign")
            @RequestBody @Valid CampaignRequest request) {
        var campaign = modelMapper.map(request.getCampaign(), CampaignDto.class);
        campaign = campaignService.update(campaignId, campaign);
        var apiCampaign = modelMapper.map(campaign, com.haapp.formicary.api.model.Campaign.class);
        return new CampaignResponse(apiCampaign);
    }

    @ApiOperation(value = "Update rating", nickname = "updateRating")
    @PatchMapping(value = "/{campaignId}/rating")
    @ResponseStatus(OK)
    public RatingResponse updateRating(
            @PathVariable Long campaignId,
            @ApiParam(value = "campaign")
            @RequestBody @Valid RatingRequest request) {
        var rating = modelMapper.map(request.getRating(), RatingDto.class);
        rating = ratingService.updateRating(campaignId, rating);
        var apiRating = modelMapper.map(rating, com.haapp.formicary.api.model.Rating.class);
        return new RatingResponse(apiRating);
    }



    @PostMapping(path = "/search")
    public CampaignsResponse searchCampaigns(/*@RequestBody SearchDevicesRequest request,*/
                                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", required = false) Integer size) {
      /*  SearchDevicesParams searchParams = mapper.map(request.getSearchDevicesParams(), SearchDevicesParams.class);
        List<SortingParams> sortingParams = mapper.map(request.getSortingParams(), new ArrayList<>(), SortingParams.class);
        Page<Device> devices = deviceService.search(searchParams, sortingParams, page - 1, size);
        SearchDevicesResponse response = new SearchDevicesResponse(
                mapper.map(devices.getContent(), new ArrayList<>(), com.ots.whoosh_service.api.v0.model.device.Device.class, ADMIN));
        response.setPageNumber(devices.getNumber() + 1);
        response.setTotalPages(devices.getTotalPages());
        response.setTotalSize(devices.getTotalElements());
        return response;*/
      return null;
    }
}
