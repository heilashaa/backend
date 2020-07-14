package com.haapp.formicary.domain.model;

import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class SearchCampaignsParams {

    private Long categoryId;

    public boolean hasCategoryId() {
        return nonNull(categoryId);
    }

    public boolean hasParams() {
        return nonNull(categoryId);
    }
}
