package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.*;
import com.haapp.formicary.infrastructure.exception.AuthException;
import com.haapp.formicary.mapping.UserMapper;
import com.haapp.formicary.persistence.model.User;
import com.haapp.formicary.persistence.model.UserLanguage;
import com.haapp.formicary.persistence.model.UserRole;
import com.haapp.formicary.persistence.model.UserTheme;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtUserDetails;
import com.haapp.formicary.security.service.AuthHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try{
            String username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed"));
            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed"));
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(username, password);
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
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

    private UserDto registeredUserInSystem(final RegistrationRequestDto registrationRequestDto) {
        String username = Optional.ofNullable(registrationRequestDto.getUsername())
                .orElseThrow(() -> new AuthException("Username should be passed"));
        String email = Optional.ofNullable(registrationRequestDto.getEmail())
                .orElseThrow(() -> new AuthException("Email should be passed"));
        String password = Optional.ofNullable(registrationRequestDto.getPassword())
                .orElseThrow(() -> new AuthException("Password should be passed"));
        if(userRepository.existsByEmail(email)){
            throw new AuthException("Email Address already in use!");
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPassword(passwordEncoder.encode(password));
        userDto.setRole(UserRole.ROLE_USER);
        userDto.setLanguage(UserLanguage.EN);
        userDto.setTheme(UserTheme.DARK);
        User user = userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
        UserDto resultUserDto = UserMapper.INSTANCE.userToUserDto(user);
        resultUserDto.setPassword(password);
        return resultUserDto;
    }

    @Transactional
    public RegistrationResponseDto registrationWithLogin(final RegistrationRequestDto registrationRequestDto) {
        UserDto userDto = registeredUserInSystem(registrationRequestDto);
        LoginRequestDto loginRequestDto = new LoginRequestDto(userDto.getUsername(), userDto.getPassword());
        LoginResponseDto loginResponseDto = login(loginRequestDto);
        return new RegistrationResponseDto(loginResponseDto.getToken());
     }

    @Transactional(readOnly = true)
    public AuthUserDto getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            User byUsername = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new AuthException("User not exist in system"));
            return UserMapper.INSTANCE.userToUserAuthDto(byUsername);
        }
        throw new AuthException("Authentication failed");
    }
}
