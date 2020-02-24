package com.haapp.formicary.domain.model;

import com.haapp.formicary.persistence.model.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    @NotNull
    @Size(min = 6)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8)
    private String password;
    private UserRole role;
    private UserTheme theme;
    private UserLanguage language;

    private List<Campaign> campaignsDto = new ArrayList<>();

    private Set<Comment> commentsDto = new HashSet<>();

    private Set<Like> likesDto = new HashSet<>();

    private Set<Rating> ratingsDto = new HashSet<>();

    private Set<Bonus> bonusesDto = new HashSet<>();;
}
