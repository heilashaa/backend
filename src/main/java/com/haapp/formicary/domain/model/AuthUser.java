package com.haapp.formicary.domain.model;

import com.haapp.formicary.persistence.model.UserLanguage;
import com.haapp.formicary.persistence.model.UserRole;
import com.haapp.formicary.persistence.model.UserTheme;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {

    private Long id;
    private String username;
    private String email;
    private UserRole role;
    private UserTheme theme;
    private UserLanguage language;
}
