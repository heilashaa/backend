package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.AuthUser;
import com.haapp.formicary.domain.model.LoginRequest;
import com.haapp.formicary.domain.model.LoginResponse;
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

    public LoginResponse login(final LoginRequest loginRequest) {
        try {
            String username = Optional.ofNullable(loginRequest.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed"));
            String password = Optional.ofNullable(loginRequest.getPassword())
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
                return new LoginResponse(token);
            } else {
                throw new AuthException("Authentication failed");
            }
        } catch (BadCredentialsException exception) {
            throw new AuthException("Username or password was incorrect. Please try again");
        }
    }

    @Transactional(readOnly = true)
    public AuthUser getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            User byUsername = userRepository.findByUsername(authentication.getName());
            return authUserTransformer.makeDto(byUsername);
        }
        throw new AuthException("Authentication failed");
    }
}
