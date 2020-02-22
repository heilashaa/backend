package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.CampaignDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapCampaignUpdate extends PropertyMap<CampaignDto, CampaignDto> {

    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getLaunchDate());
        skip(destination.getModificationDate());
        skip(destination.getUser());
    }
}
