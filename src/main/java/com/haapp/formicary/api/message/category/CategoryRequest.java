package com.haapp.formicary.api.message.category;

import com.haapp.formicary.domain.model.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryRequest {

    @Valid
    @NotNull
    private CategoryDto categoryDto;
}
