package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.AuthUserDto;
import com.haapp.formicary.domain.model.LoginRequestDto;
import com.haapp.formicary.domain.model.LoginResponseDto;
import com.haapp.formicary.infrastructure.exception.AuthException;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtUserDetails;
import com.haapp.formicary.security.service.AuthHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthUserTransformer authUserTransformer;
    private final AuthHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            String username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed"));
            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed"));
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                    password);
            // Try to authenticate with this token
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
            // Set generated JWT token to response header
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
                if (!userRepository.existsById(userDetails.getId())) {
                    throw new AuthException("User not exist in system");
                }
                String token = this.authenticationHelper.generateToken(userDetails.getId());
                return new LoginResponseDto(token);
            } else {
                throw new AuthException("Authentication failed");
            }
        } catch (BadCredentialsException exception) {
            throw new AuthException("Username or password was incorrect. Please try again");
        }
    }

    @Transactional(readOnly = true)
    public AuthUserDto getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            User byUsername = userRepository.findByUsername(authentication.getName());
            return authUserTransformer.makeDto(byUsername);
        }
        throw new AuthException("Authentication failed");
    }
}
