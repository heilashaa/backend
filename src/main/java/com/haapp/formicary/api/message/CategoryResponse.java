package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse extends BaseResponse{

    private CategoryDto categoryDto;
}
