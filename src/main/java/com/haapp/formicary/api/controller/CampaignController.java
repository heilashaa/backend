package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.*;
import com.haapp.formicary.api.model.CampaignApi;
import com.haapp.formicary.api.model.RatingApi;
import com.haapp.formicary.api.model.SearchCampaignsParamsApi;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.Rating;
import com.haapp.formicary.domain.service.CampaignService;
import com.haapp.formicary.domain.service.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for campaigns.")
@RestController
@RequestMapping(value = "/api/v1/campaigns", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class CampaignController {

    private final ModelMapper modelMapper;
    private final CampaignService campaignService;
    private final RatingService ratingService;

    @ApiOperation(value = "Fulltext search campaigns", nickname = "searchCampaigns")
    @GetMapping(value = "/search")
    @ResponseStatus(OK)
    public CampaignsResponse search(@RequestParam("searchValue") String searchValue) {
        var campaigns = campaignService.search(searchValue);
        List<CampaignApi> apiCampaigns = asList(modelMapper.map(campaigns, CampaignApi[].class));
        return new CampaignsResponse(apiCampaigns);
    }

    @ApiOperation(value = "Get campaigns", nickname = "getBySearchParams")
    @GetMapping
    @ResponseStatus(OK)
    public PageResponse<CampaignApi> getBySearchParams(SearchCampaignsParamsApi searchCampaignsParams,
                                                       @RequestParam(required = false) List<String> sortingParams,
                                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", required = false, defaultValue = "100") Integer size) {

        var searchParams = modelMapper.map(searchCampaignsParams, com.haapp.formicary.domain.model.SearchCampaignsParams.class);
        var result = campaignService.findAll(searchParams, sortingParams, page - 1, size);
        Type pageType = new TypeToken<PageResponse<CampaignApi>>() {
        }.getType();
        return modelMapper.map(result, pageType);
    }

    @ApiOperation(value = "Get campaign", nickname = "getCampaign")
    @GetMapping(value = "/{campaignId}")
    @ResponseStatus(OK)
    public CampaignResponse getCampaign(@PathVariable Long campaignId) {
        var campaign = campaignService.findByIdRequired(campaignId);
        var apiCampaign = modelMapper.map(campaign, CampaignApi.class);
        return new CampaignResponse(apiCampaign);
    }


    @ApiOperation(value = "Update rating", nickname = "updateRating")
    @PatchMapping(value = "/{campaignId}/rating")
    @ResponseStatus(OK)
    public RatingResponse updateRating(
            @PathVariable Long campaignId,
            @ApiParam(value = "campaign")
            @RequestBody @Valid RatingRequest request) {
        var rating = modelMapper.map(request.getRating(), Rating.class);
        rating = ratingService.updateRating(campaignId, rating);
        var apiRating = modelMapper.map(rating, RatingApi.class);
        return new RatingResponse(apiRating);
    }
}
