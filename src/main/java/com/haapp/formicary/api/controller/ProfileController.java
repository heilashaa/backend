package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.*;
import com.haapp.formicary.api.model.CampaignApi;
import com.haapp.formicary.api.model.UserBonusApi;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.Campaign;
import com.haapp.formicary.domain.service.BonusService;
import com.haapp.formicary.domain.service.CampaignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "API for profiles.")
@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class ProfileController {

    private CampaignService campaignService;
    private BonusService bonusService;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Add new campaign", nickname = "createCampaign")
    @PostMapping(value = "/{userId}/campaigns")
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public CampaignResponse createCampaign(
            @PathVariable Long userId,
            @RequestBody @Valid CampaignRequest request) {
        var campaign = modelMapper.map(request.getCampaign(), Campaign.class);
        campaign = campaignService.create(userId, campaign);
        var apiCampaign = modelMapper.map(campaign, CampaignApi.class);
        return new CampaignResponse(apiCampaign);
    }

    @ApiOperation(value = "Get campaigns by user", nickname = "getCampaignsByUser")
    @GetMapping(value = "/{userId}/campaigns")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public CampaignsResponse getCampaignsByUser(@PathVariable Long userId) {
        var campaigns = campaignService.findByUser(userId);
        List<CampaignApi> apiCampaigns = asList(modelMapper.map(campaigns, CampaignApi[].class));
        return new CampaignsResponse(apiCampaigns);
    }


    @ApiOperation(value = "Update campaign", nickname = "updateCampaign")
    @PutMapping(value = "/{userId}/campaigns/{campaignId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public CampaignResponse updateCampaign(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @ApiParam(value = "campaign")
            @RequestBody @Valid CampaignRequest request) {
        var campaign = modelMapper.map(request.getCampaign(), Campaign.class);
        campaign = campaignService.update(campaignId, campaign);
        var apiCampaign = modelMapper.map(campaign, CampaignApi.class);
        return new CampaignResponse(apiCampaign);
    }


    @ApiOperation(value = "Add bonus to user", nickname = "addBonusToUser")
    @PostMapping(value = "/{userId}/bonuses/{bonusId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public UserBonusResponse addBonusToUser(@PathVariable Long userId, @PathVariable Long bonusId) {
        var bonus = bonusService.addBonusToUser(userId, bonusId);
        var apiBonus = modelMapper.map(bonus, UserBonusApi.class);
        return new UserBonusResponse(apiBonus);
    }

    @ApiOperation(value = "Get user bonuses", nickname = "getBonusesByUser")
    @GetMapping(value = "/{userId}/bonuses")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public UserBonusesResponse getUserBonuses(@PathVariable Long userId) {
        var bonuses = bonusService.findByUserId(userId);
        List<UserBonusApi> apiUserBonuses = asList(modelMapper.map(bonuses, UserBonusApi[].class));
        return new UserBonusesResponse(apiUserBonuses);
    }
}
