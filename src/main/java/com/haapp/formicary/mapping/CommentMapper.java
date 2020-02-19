package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.CommentDtoQQQQ;
import com.haapp.formicary.domain.model.CommentDto;
import com.haapp.formicary.persistence.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, UserMapper.class, LikeMapper.class})
public interface CommentMapper {
    
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment commentDtoToComment(CommentDto commentDto);

    CommentDto commentToCommentDto(Comment comment);
}
