package com.haapp.formicary.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private String video;
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    private LocalDateTime modificationDate;

    private User user;

    private Category category;

    private Set<Bonus> bonuses = new HashSet<>();

    private Set<Comment> comments = new HashSet<>();

    private Set<Article> articles = new HashSet<>();

    private Set<Rating> ratings = new HashSet<>();

    private Set<Image> images = new HashSet<>();

    private Set<Tag> tags;
}
