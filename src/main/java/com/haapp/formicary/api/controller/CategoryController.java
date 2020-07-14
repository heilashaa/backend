package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.CategoriesResponse;
import com.haapp.formicary.domain.model.Category;
import com.haapp.formicary.domain.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for Category.")
@RestController
@RequestMapping(value = "/api/v1/categories", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @ApiOperation(value = "Select all categories")
    @GetMapping()
    @ResponseStatus(OK)
    public CategoriesResponse getCategories() {
        List<Category> categoriesDto = categoryService.getAll();
        return new CategoriesResponse(categoriesDto);
    }
}
