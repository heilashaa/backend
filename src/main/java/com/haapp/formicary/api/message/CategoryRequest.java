package com.haapp.formicary.api.message;

import com.haapp.formicary.api.model.CategoryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryRequest {

    @Valid
    @NotNull
    private CategoryDto categoryDto;
}
