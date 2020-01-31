package com.haapp.formicary.persistence.model;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer status;
    private Integer theme;
    private Integer language;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Campaign> campaigns;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Like> likes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Rating> ratings;
}
