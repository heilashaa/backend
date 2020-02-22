package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.CampaignDto;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.persistence.model.Campaign;
import com.haapp.formicary.persistence.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CampaignService {

    private ModelMapper modelMapper;
    private CampaignRepository campaignRepository;
    private UserService userService;

    public CampaignDto create(CampaignDto campaign){
        if(nonNull(campaign)) {
            campaign.setUser(userService.getCurrentUser());
            return save(campaign);
        }
        return null;
    }

    public CampaignDto update(Long id, CampaignDto campaign){
        var saved = findByIdRequired(id);
        modelMapper.map(campaign, saved);
        return save(saved);
    }

    public List<CampaignDto> findByLoggedUser(){
        var user = userService.getCurrentUser();
        var campaigns = campaignRepository.findDistinctByUserId(user.getId());
        return asList(modelMapper.map(campaigns, CampaignDto[].class));
    }

    public CampaignDto findByIdRequired(Long id){
        var optional = campaignRepository.findById(id);
        return optional.map(campaign -> modelMapper.map(campaign, CampaignDto.class))
                .orElseThrow(()-> new NotFoundException(""));
    }


    public CampaignDto save(CampaignDto campaign) {
        var dataCampaign = modelMapper.map(campaign, Campaign.class);
        dataCampaign  = campaignRepository.save(dataCampaign);
        return modelMapper.map(dataCampaign, CampaignDto.class);
    }



    public com.haapp.formicary.domain.model.Page<CampaignDto> findAll(Integer size, Integer page) {
     //   PageRequest pageRequest = PageRequest.of(page, size, new Sort(DESC, "id"));
     //   Page<Campaign> result = campaignRepository.findAll(pageRequest);
        return null;
    }
}
