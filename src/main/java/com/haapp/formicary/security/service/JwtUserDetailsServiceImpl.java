package com.haapp.formicary.security.service;

import com.haapp.formicary.domain.model.User;
import com.haapp.formicary.infrastructure.exception.AuthException;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var dataUser = userRepository.findByEmail(s).
                orElseThrow(() -> new AuthException(USER_NOT_FOUND));;
        return new JwtUserDetails(modelMapper.map(dataUser, User.class));
    }
}
