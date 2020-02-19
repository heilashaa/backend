package com.haapp.formicary.api.message.category;

import com.haapp.formicary.api.message.BaseResponse;
import com.haapp.formicary.api.model.CategoryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesResponse extends BaseResponse {

    private List<CategoryDto> categoriesDto;
}
