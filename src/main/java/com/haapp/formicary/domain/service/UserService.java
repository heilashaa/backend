package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.UserDto;
import com.haapp.formicary.persistence.model.User;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtUserDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (JwtUserDetails) authentication.getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        return modelMapper.map(user, UserDto.class);
    }
}
