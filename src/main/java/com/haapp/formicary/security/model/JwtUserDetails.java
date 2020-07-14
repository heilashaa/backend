package com.haapp.formicary.security.model;

import com.haapp.formicary.domain.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class JwtUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;

    public JwtUserDetails(User userDto) {
        this.id = userDto.getId();
        this.username = userDto.getEmail();
        this.password = userDto.getPassword();
        this.authorities = new HashSet<>();
        this.authorities.add(new SimpleGrantedAuthority(userDto.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
