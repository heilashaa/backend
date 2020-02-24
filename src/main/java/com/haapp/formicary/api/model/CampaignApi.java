package com.haapp.formicary.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haapp.formicary.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class CampaignApi {

    private Long id;
    private String name;
    private String description;
    private Integer targetAmount;
    private String video;
    private LocalDateTime launchDate;
    private LocalDateTime expirationDate;
    private LocalDateTime modificationDate;
    private Integer collectedAmount;

    private UserApi user;
    private List<ImageApi> images;
    private CampaignRatingApi rating;
    private List<TagApi> tags;
    private CategoryApi category;

    @JsonProperty
    public long getPercent(){
        if(nonNull(targetAmount) && nonNull(collectedAmount)){
            return Math.round(collectedAmount * 100.0/targetAmount);
        }
        return 0;
    }
}
