package com.haapp.formicary.api.message;

import java.io.Serializable;

import com.haapp.formicary.api.model.SearchCampaignsParamsApi;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTripsRequest implements Serializable {

    private SearchCampaignsParamsApi searchParams;
}
