package com.haapp.formicary.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCampaignsParamsApi implements Serializable {

    private String categoryId;
}
