package com.haapp.formicary.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BonusDto {

    private Long id;
    private String name;
    private String description;
    private Integer amount;

    private CampaignDto campaign;

    private Set<UserDto> users = new HashSet<>();
}
