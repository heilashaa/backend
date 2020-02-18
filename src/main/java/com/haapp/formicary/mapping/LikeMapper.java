package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.LikeDto;
import com.haapp.formicary.domain.model.Like;
import com.haapp.formicary.persistence.model.LikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CommentMapper.class, UserMapper.class})
public interface LikeMapper {
    
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

    LikeDto likeDomainToLikeDto(Like likeDomain);

    Like likeDtoToLikeDomain(LikeDto likeDto);

    LikeEntity likeDomainToLikePersistence(Like likeDomain);

    Like likePersistenceToLikeDomain(LikeEntity likePersistence);
}
