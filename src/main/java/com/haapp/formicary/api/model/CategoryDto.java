package com.haapp.formicary.api.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Category")
public class CategoryDto {

    private Long id;

    @NotNull
    @Size(min = 1, max = 30)
    private String name;
//    private Set<CampaignDto> campaigns = new HashSet<>();
}
