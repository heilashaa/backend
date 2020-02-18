package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.ImageDto;
import com.haapp.formicary.domain.model.Image;
import com.haapp.formicary.persistence.model.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class})
public interface ImageMapper {
    
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto imageDomainToImageDto(Image imageDomain);

    Image imageDtoToImageDomain(ImageDto imageDto);

    ImageEntity imageDomainToImagePersistence(Image imageDomain);

    Image imagePersistenceToImageDomain(ImageEntity imagePersistence);
}
