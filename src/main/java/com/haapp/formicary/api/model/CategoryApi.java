package com.haapp.formicary.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryApi {


    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
}
