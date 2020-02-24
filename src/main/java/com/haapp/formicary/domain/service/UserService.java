package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.User;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
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

    private UserRepository repository;
    private ModelMapper modelMapper;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (JwtUserDetails) authentication.getPrincipal();
        com.haapp.formicary.persistence.model.User user = repository.getOne(userDetails.getId());
        return modelMapper.map(user, User.class);
    }

    public User findByIdRequired(Long id){
        var optional = repository.findById(id);
        return optional.map(user -> modelMapper.map(user, User.class))
                .orElseThrow(()-> new NotFoundException(""));
    }
}
