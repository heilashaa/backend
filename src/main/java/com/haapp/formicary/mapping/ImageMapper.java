package com.haapp.formicary.mapping;

import com.haapp.formicary.domain.model.ImageDto;
import com.haapp.formicary.persistence.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class})
public interface ImageMapper {
    
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    Image imageDtoToImage(ImageDto imageDto);

    ImageDto imageToImageDto(Image image);
}
