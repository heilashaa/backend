package com.haapp.formicary.mapping;

import com.haapp.formicary.domain.model.AuthUserDto;
import com.haapp.formicary.domain.model.UserDto;
import com.haapp.formicary.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class, CommentMapper.class, LikeMapper.class, RatingMapper.class, BonusMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    AuthUserDto userToUserAuthDto(User user);
}
