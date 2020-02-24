package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.Like;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapLikeUpdate extends PropertyMap<Like, Like> {

    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getUser());
        skip(destination.getComment());
    }
}
