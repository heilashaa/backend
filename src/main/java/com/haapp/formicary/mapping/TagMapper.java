package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.TagDto;
import com.haapp.formicary.domain.model.Tag;
import com.haapp.formicary.persistence.model.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class})
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto tagDomainToTagDto(Tag tagDomain);

    Tag tagDtoToTagDomain(TagDto tagDto);

    TagEntity tagDomainToTagPersistence(Tag tagDomain);

    Tag tagPersistenceToTagDomain(TagEntity tagPersistence);
}
