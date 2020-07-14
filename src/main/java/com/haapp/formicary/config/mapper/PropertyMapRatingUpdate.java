package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.Rating;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapRatingUpdate extends PropertyMap<Rating, Rating> {

    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getUser());
        skip(destination.getCampaign());
    }
}
