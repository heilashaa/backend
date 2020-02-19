package com.haapp.formicary.api.message.category;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.domain.model.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse extends BaseResponse {

    private CategoryDto categoryDto;
}
