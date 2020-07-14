package com.haapp.formicary.api.message;

import com.haapp.formicary.domain.model.Category;
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

    private List<Category> categories;
}
