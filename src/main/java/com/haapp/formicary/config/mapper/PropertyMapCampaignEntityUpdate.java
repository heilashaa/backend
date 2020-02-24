package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.Campaign;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapCampaignEntityUpdate extends PropertyMap<Campaign, com.haapp.formicary.persistence.model.Campaign> {

    @Override
    protected void configure() {
        skip(destination.getRating());
    }
}
