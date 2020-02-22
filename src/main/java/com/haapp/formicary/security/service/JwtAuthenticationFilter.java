package com.haapp.formicary.security.service;

import com.haapp.formicary.security.handler.RestAuthenticationFailureHandler;
import com.haapp.formicary.security.model.JwtAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.TOKEN_NOT_FOUND_IN_REQUEST_HEADER;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(request -> true);
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String token = Optional.ofNullable(request.getHeader(AuthHelper.AUTHENTICATION_HEADER))
                    .map(header -> header.substring(7)).orElse(null);
            if (Objects.isNull(token)) {
                throw new BadCredentialsException(TOKEN_NOT_FOUND_IN_REQUEST_HEADER);
            }
            JwtAuthentication authRequest = new JwtAuthentication(token);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (AuthenticationException e) {
            unsuccessfulAuthentication(request, response, e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        if (this.eventPublisher != null) {
            this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
        chain.doFilter(request, response);
    }
}
