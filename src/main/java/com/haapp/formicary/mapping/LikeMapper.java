package com.haapp.formicary.mapping;

import com.haapp.formicary.domain.model.LikeDto;
import com.haapp.formicary.persistence.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CommentMapper.class, UserMapper.class})
public interface LikeMapper {
    
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

    Like likeDtoToLike(LikeDto likeDto);

    LikeDto likeToLikeDto(Like like);
}
