package com.haapp.formicary.config.mapper;

import com.haapp.formicary.domain.model.ArticleDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapArticleUpdate extends PropertyMap<ArticleDto, ArticleDto> {

    @Override
    protected void configure() {
        skip(destination.getId());
        skip(destination.getCreationDate());
        skip(destination.getCampaign());
    }
}
