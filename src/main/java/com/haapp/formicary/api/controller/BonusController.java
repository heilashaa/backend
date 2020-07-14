package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.BonusRequest;
import com.haapp.formicary.api.message.BonusResponse;
import com.haapp.formicary.api.message.BonusesResponse;
import com.haapp.formicary.api.message.ConfirmationResponse;
import com.haapp.formicary.api.model.BonusApi;
import com.haapp.formicary.config.ApiService;
import com.haapp.formicary.domain.model.Bonus;
import com.haapp.formicary.domain.service.BonusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.haapp.formicary.api.message.ConfirmationResponse.SUCCESS;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for campaigns.")
@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@ApiService
public class BonusController {

    private final ModelMapper modelMapper;
    private final BonusService bonusService;

    @ApiOperation(value = "Add bonus to campaign", nickname = "addBonus")
    @PostMapping(value = "/{userId}/campaigns/{campaignId}/bonuses")
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public BonusResponse addBonus(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @ApiParam(value = "bonus")
            @RequestBody @Valid BonusRequest request) {
        var bonus = modelMapper.map(request.getBonus(), Bonus.class);
        bonus = bonusService.create(campaignId, bonus);
        var apiBonus = modelMapper.map(bonus, BonusApi.class);
        return new BonusResponse(apiBonus);
    }

    @ApiOperation(value = "Get bonuses by campaign", nickname = "getBonusesByCampaign")
    @GetMapping(value = "/{userId}/campaigns/{campaignId}/bonuses")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public BonusesResponse getBonusesByCampaign(@PathVariable Long userId, @PathVariable Long campaignId) {
        var bonuses = bonusService.findByCampaignId(campaignId);
        List<BonusApi> apiBonuses = asList(modelMapper.map(bonuses, BonusApi[].class));
        return new BonusesResponse(apiBonuses);
    }

    @ApiOperation(value = "Update bonus", nickname = "updateBonus")
    @PutMapping(value = "/{userId}/campaigns/{campaignId}/bonuses/{bonusId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public BonusResponse updateBonus(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @PathVariable Long bonusId,
            @ApiParam(value = "bonus")
            @RequestBody @Valid BonusRequest request) {
        var bonus = modelMapper.map(request.getBonus(), Bonus.class);
        bonus = bonusService.update(bonusId, bonus);
        var apiBonus = modelMapper.map(bonus, BonusApi.class);
        return new BonusResponse(apiBonus);
    }

    @ApiOperation(value = "Delete bonus", nickname = "deleteBonus")
    @DeleteMapping(value = "/{userId}/campaigns/{campaignId}/bonuses/{bonusId}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or isOwner(#userId)")
    public ConfirmationResponse deleteBonus(
            @PathVariable Long userId,
            @PathVariable Long campaignId,
            @PathVariable Long bonusId) {
        bonusService.delete(bonusId);
        return SUCCESS;
    }

    @ApiOperation(value = "Get bonus", nickname = "getBonus")
    @GetMapping(value = "/bonuses/{bonusId}")
    @ResponseStatus(OK)
    public BonusResponse getBonus(@PathVariable Long bonusId) {
        var bonus = bonusService.findByIdRequired(bonusId);
        var apiBonus = modelMapper.map(bonus, BonusApi.class);
        return new BonusResponse(apiBonus);
    }
}
