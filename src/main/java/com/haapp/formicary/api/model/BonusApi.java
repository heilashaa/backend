package com.haapp.formicary.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BonusApi {


    private Long id;
    private String name;
    private String description;
    private Integer amount;
    private CampaignApi campaign;
}
