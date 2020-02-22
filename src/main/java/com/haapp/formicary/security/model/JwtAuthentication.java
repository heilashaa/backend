package com.haapp.formicary.security.model;

import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.CAN_SET_THIS_TOKEN;

public class JwtAuthentication implements Authentication {

    private final JwtUserDetails userDetails;

    private final Serializable credentials;

    private final Collection<? extends GrantedAuthority> grantedAuthorities;

    private boolean isAuthenticated;

    public JwtAuthentication(final String token) {
        this.credentials = token;
        this.userDetails = null;
        this.grantedAuthorities = null;
    }

    public JwtAuthentication(final JwtUserDetails userDetails) {
        this.credentials = null;
        this.userDetails = userDetails;
        this.grantedAuthorities = ImmutableSet.copyOf(userDetails.getAuthorities());
        this.isAuthenticated = true;
    }

    @Override
    public String getName() {
        return Objects.isNull(this.userDetails) ? null : this.userDetails.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public Serializable getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return this.userDetails;
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(CAN_SET_THIS_TOKEN);
        }
        this.isAuthenticated = false;
    }
}
