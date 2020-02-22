package com.haapp.formicary.api.model;

import com.haapp.formicary.domain.model.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Campaign {

    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private String video;
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    private LocalDateTime modificationDate;
    private CampaignRating rating;

    private CategoryDto category;
}
