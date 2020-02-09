package com.haapp.formicary.api.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {

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
