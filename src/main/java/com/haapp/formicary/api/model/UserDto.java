package com.haapp.formicary.api.model;

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
public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private UserRole role;
    private Integer status;
    private Integer theme;
    private Integer language;

    private List<CampaignDto> campaigns = new ArrayList<>();

    private Set<CommentDto> comments = new HashSet<>();

    private Set<LikeDto> likes = new HashSet<>();

    private Set<RatingDto> ratings = new HashSet<>();

    private Set<BonusDto> bonuses  = new HashSet<>();
}
