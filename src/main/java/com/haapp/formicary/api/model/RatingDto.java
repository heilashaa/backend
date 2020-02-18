package com.haapp.formicary.api.model;

import com.haapp.formicary.persistence.model.CampaignEntity;
import com.haapp.formicary.persistence.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    private Long id;
    private Integer value;

    private UserDto user;

    private CampaignDto campaign;
}
