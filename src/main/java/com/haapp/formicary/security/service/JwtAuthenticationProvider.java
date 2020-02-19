package com.haapp.formicary.security.service;

import com.haapp.formicary.domain.model.User;
import com.haapp.formicary.infrastructure.exception.InvalidTokenAuthException;
import com.haapp.formicary.mapping.UserMapper;
import com.haapp.formicary.persistence.model.UserEntity;
import com.haapp.formicary.persistence.repository.UserRepository;
import com.haapp.formicary.security.model.JwtAuthentication;
import com.haapp.formicary.security.model.JwtUserDetails;
import com.haapp.formicary.security.model.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLIS_IN_SECOND = 1000L;

    private final UserRepository userRepository;
    private final AuthHelper authenticationHelper;

    @Override
    public Authentication authenticate(final Authentication authRequest) {
        String token = StringUtils.trimToNull((String) authRequest.getCredentials());
        TokenPayload tokenPayload = authenticationHelper.decodeToken(token);
        checkIsExpired(tokenPayload.getExpiration());
        Long userEntityId = tokenPayload.getUserId();
        if (Objects.isNull(userEntityId)) {
            throw new InvalidTokenAuthException("Token does not contain a user id");
        }
        UserEntity userEntity = userRepository.findById(userEntityId)
                .orElseThrow(() -> new InvalidTokenAuthException("Token does not contain existed user id"));
        User user = UserMapper.INSTANCE.userPersistenceToUserDomain(userEntity);
        JwtUserDetails userDetails = new JwtUserDetails(user);
        return new JwtAuthentication(userDetails);
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if ((System.currentTimeMillis() / MILLIS_IN_SECOND) > tokenExpirationTime) {
            throw new InvalidTokenAuthException("Authentication token is expired");
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }
}