package com.haapp.formicary.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserTheme theme;
    @Column
    @Enumerated(EnumType.STRING)
    private UserLanguage language;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Campaign> campaigns = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Like> likes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_bonus",
            joinColumns = @JoinColumn(name = "user_id"), foreignKey = @ForeignKey(name = "bonus_user_FK"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id") , inverseForeignKey = @ForeignKey(name = "user_bonus_FK"))
    private Set<Bonus> bonuses  = new HashSet<>();
}
