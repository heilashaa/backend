package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.RatingDtoQQQ;
import com.haapp.formicary.domain.model.RatingDto;
import com.haapp.formicary.persistence.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, UserMapper.class})
public interface RatingMapper {
    
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    Rating ratingDtoToRating(RatingDto ratingDto);

    RatingDto ratingToRatingDto(Rating rating);
}
