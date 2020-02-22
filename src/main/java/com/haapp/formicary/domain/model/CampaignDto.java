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
public class CampaignDto {

    private Long id;
    private UserDto user;
    private String name;
    private String description;
    private Integer targetAmount;
    private String video;
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    private LocalDateTime modificationDate;

    private CampaignRating rating;

    private CategoryDto category;

    private Set<BonusDto> bonuses = new HashSet<>();

    private Set<CommentDto> comments = new HashSet<>();

   // private Set<ArticleDto> articles = new HashSet<>();

    private Set<ImageDto> images = new HashSet<>();

    private Set<TagDto> tags;
}
