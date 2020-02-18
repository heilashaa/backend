package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.UserDto;
import com.haapp.formicary.domain.model.User;
import com.haapp.formicary.persistence.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, CommentMapper.class, LikeMapper.class, RatingMapper.class, BonusMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userDomainToUserDto(User userDomain);

    User userDtoToUserDomain(UserDto userDto);

    UserEntity userDomainToUserPersistence(User userDomain);

    User userPersistenceToUserDomain(UserEntity userPersistence);
}
