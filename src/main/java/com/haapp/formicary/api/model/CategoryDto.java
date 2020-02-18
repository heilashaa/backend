package com.haapp.formicary.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;
    @NotNull
    @Size(min = 1, max = 30)
    private String name;
//    private Set<CampaignDto> campaigns = new HashSet<>();
}
