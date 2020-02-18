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
public class TagDto {

    private Long id;
    private String name;

    private Set<CampaignDto> campaigns = new HashSet<>();
}
