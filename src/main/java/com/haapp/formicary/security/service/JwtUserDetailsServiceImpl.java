package com.haapp.formicary.security.service;

import com.haapp.formicary.infrastructure.exception.AuthException;
import com.haapp.formicary.mapping.UserMapper;
import com.haapp.formicary.persistence.model.UserEntity;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity usernameEntity = userRepository.findByUsername(s).
                orElseThrow(() -> new AuthException(USER_NOT_FOUND));;
        return new JwtUserDetails(UserMapper.INSTANCE.userPersistenceToUserDomain(usernameEntity));

    }
}
