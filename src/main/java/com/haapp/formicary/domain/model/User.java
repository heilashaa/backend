package com.haapp.formicary.domain.model;

import com.haapp.formicary.persistence.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private Integer status;
    private Integer theme;
    private Integer language;

    private List<Campaign> campaigns = new ArrayList<>();

    private Set<Comment> comments = new HashSet<>();

    private Set<Like> likes = new HashSet<>();

    private Set<Rating> ratings = new HashSet<>();

    private Set<Bonus> bonuses  = new HashSet<>();;
}
