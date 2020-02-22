package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.LikeDto;
import com.haapp.formicary.domain.model.RatingDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapLikeUpdate extends PropertyMap<LikeDto, LikeDto> {

    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getUser());
        skip(destination.getComment());
    }
}
