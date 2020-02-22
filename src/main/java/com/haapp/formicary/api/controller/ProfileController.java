package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.CampaignsResponse;
import com.haapp.formicary.api.model.Campaign;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.service.CampaignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "API for profiles.")
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class ProfileController {

    private CampaignService campaignService;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Add new article to campaign", nickname = "getCampaignsForProfile")
    @PostMapping(value = "/campaigns")
    @ResponseStatus(OK)
    public CampaignsResponse getCampaignsForProfile() {
        var campaigns = campaignService.findByLoggedUser();
        List<Campaign> apiCampaigns =  asList(modelMapper.map(campaigns, Campaign[].class));
        return new CampaignsResponse(apiCampaigns);
    }
}
