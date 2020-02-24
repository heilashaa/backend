package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.*;
import com.haapp.formicary.infrastructure.exception.AuthException;
import com.haapp.formicary.persistence.model.UserLanguage;
import com.haapp.formicary.persistence.model.UserRole;
import com.haapp.formicary.persistence.model.UserTheme;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtUserDetails;
import com.haapp.formicary.security.service.AuthHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    public Token login(final LoginData loginRequestDto) {
        try{
            String email = Optional.ofNullable(loginRequestDto.getEmail())
                    .orElseThrow(() -> new BadCredentialsException(EMAIL_SHOULD_BE_PASSED));
            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException(PASSWORD_SHOULD_BE_PASSED));
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(email, password);
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
                if (!userRepository.existsById(userDetails.getId())) {
                    throw new AuthException(USER_NOT_EXIST);
                }
                String token = this.authenticationHelper.generateToken(userDetails.getId());
                return new Token(token);
            } else {
                throw new AuthException(AUTHENTICATION_FAILED);
            }
        } catch (BadCredentialsException exception) {
            throw new AuthException(INCORRECT_AYTH_DATA);
        }
    }

    private User registeredUserInSystem(final RegistrationData registrationRequestDto) {
        String username = Optional.ofNullable(registrationRequestDto.getUsername())
                .orElseThrow(() -> new AuthException(USERNAME_SHOULD_BE_PASSED));
        String email = Optional.ofNullable(registrationRequestDto.getEmail())
                .orElseThrow(() -> new AuthException(EMAIL_SHOULD_BE_PASSED));
        String password = Optional.ofNullable(registrationRequestDto.getPassword())
                .orElseThrow(() -> new AuthException(PASSWORD_SHOULD_BE_PASSED));
        if(userRepository.existsByEmail(email)){
            throw new AuthException(EMAIL_ALREADY_USE);
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.ROLE_USER);
        user.setLanguage(UserLanguage.EN);
        user.setTheme(UserTheme.DARK);
        var dataUser = userRepository.save(modelMapper.map(user, com.haapp.formicary.persistence.model.User.class));
        user  = modelMapper.map(dataUser, User.class);
        user.setPassword(password);
        return user;
    }

    @Transactional
    public Token registrationAndLogin(final RegistrationData registrationRequestDto) {
        User userDto = registeredUserInSystem(registrationRequestDto);
        LoginData loginRequestDto = new LoginData(userDto.getEmail(), userDto.getPassword());
        return login(loginRequestDto);
    }

    @Transactional(readOnly = true)
    public AuthUser getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            var user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new AuthException(USER_NOT_EXIST));
            return modelMapper.map(user, AuthUser.class);
        }
        throw new AuthException(AUTHENTICATION_FAILED);
    }

    @Transactional
    public Token socialLogin(RegistrationData registrationRequestDto) {
        String email = Optional.ofNullable(registrationRequestDto.getEmail())
                .orElseThrow(() -> new AuthException(SOCIAL_LOGIN_FAILED));
        if(userRepository.existsByEmail(email)){
            try{
                LoginData loginRequestDto = new LoginData(
                        registrationRequestDto.getEmail(),
                        registrationRequestDto.getPassword());
                return login(loginRequestDto);
            } catch (BadCredentialsException exception) {
                throw new AuthException(SOCIAL_LOGIN_FAILED);
            }
        }else{
            return registrationAndLogin(registrationRequestDto);
        }
    }
}
