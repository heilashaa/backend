package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.RatingDto;
import com.haapp.formicary.domain.model.Rating;
import com.haapp.formicary.persistence.model.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, UserMapper.class})
public interface RatingMapper {
    
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingDto ratingDomainToRatingDto(Rating ratingDomain);

    Rating ratingDtoToRatingDomain(RatingDto ratingDto);

    RatingEntity ratingDomainToRatingPersistence(Rating ratingDomain);

    Rating ratingPersistenceToRatingDomain(RatingEntity ratingPersistence);
}
