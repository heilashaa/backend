package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.CommentDto;
import com.haapp.formicary.domain.model.Comment;
import com.haapp.formicary.persistence.model.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, UserMapper.class, LikeMapper.class})
public interface CommentMapper {
    
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto commentDomainToCommentDto(Comment commentDomain);

    Comment commentDtoToCommentDomain(CommentDto commentDto);

    CommentEntity commentDomainToCommentPersistence(Comment commentDomain);

    Comment commentPersistenceToCommentDomain(CommentEntity commentPersistence);
}
