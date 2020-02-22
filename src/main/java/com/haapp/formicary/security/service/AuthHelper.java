package com.haapp.formicary.security.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haapp.formicary.infrastructure.exception.InvalidTokenAuthException;
import com.haapp.formicary.security.model.TokenPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.*;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    public static final String AUTHENTICATION_HEADER = "Authorization";

    public static final String AUTHENTICATION_PARAM = "auth";

    private final String SECRET = "ABCDabcd";

    private final ObjectMapper objectMapper;

    private Long tokenExpirationTime = 3600L;

    public String generateToken(final Long userId) {
        try {
            TokenPayload payload = new TokenPayload(
                userId,
                Instant.now().getEpochSecond() + this.tokenExpirationTime
            );
            String token = this.objectMapper.writeValueAsString(payload);
            return JwtHelper.encode(token, new MacSigner(SECRET)).getEncoded();
        } catch (JsonProcessingException e) {
            throw new InternalAuthenticationServiceException(ERROR_GENERATING_TOKEN, e);
        }
    }

    public TokenPayload decodeToken(final String token) {
        if (Objects.isNull(token)) {
            throw new InvalidTokenAuthException(TOKEN_NULL_OR_BLANK);
        }
        Jwt jwt = JwtHelper.decode(token);
        try {
            jwt.verifySignature(new MacSigner(SECRET));
        } catch (Exception exception) {
            throw new InvalidTokenAuthException(TOKEN_SIGNATURE_VERIFICATION_FAILED);
        }
        String claims = jwt.getClaims();
        TokenPayload tokenPayload;
        try {
            tokenPayload = this.objectMapper.readValue(claims, TokenPayload.class);
        } catch (IOException exception) {
            throw new InvalidTokenAuthException(TOKEN_PARSING_FAILED);
        }
        return tokenPayload;
    }
}
