package com.haapp.formicary.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    private LocalDateTime modificationDate;

//    private User user;
//
//    private Category category;
//
//    private Set<Bonus> bonuses;
//
//    private Set<Comment> comments;
//
//    private Set<News> news;
//
//    private Set<Rating> ratings;
//
//    private Set<Tag> tags;
}
