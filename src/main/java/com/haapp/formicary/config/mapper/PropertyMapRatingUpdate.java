package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.RatingDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapRatingUpdate extends PropertyMap<RatingDto, RatingDto> {

    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getUser());
        skip(destination.getCampaign());
    }
}
