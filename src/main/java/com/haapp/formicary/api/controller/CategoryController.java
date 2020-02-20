package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.category.CategoriesResponse;
import com.haapp.formicary.api.message.category.CategoryRequest;
import com.haapp.formicary.api.message.category.CategoryResponse;
import com.haapp.formicary.domain.model.CategoryDto;
import com.haapp.formicary.domain.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "REST API for Category. API allows operations with category: create, read, update, delete."
        /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
@RestController
@RequestMapping(value = "/api/v1/category", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @ApiOperation(value = "Select all categories"/*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping()
    @ResponseStatus(OK)
    public CategoriesResponse getCategories() {
        List<CategoryDto> categoriesDto = categoryService.getAll();
        return new CategoriesResponse(categoriesDto);
    }

    @ApiOperation(value = "Find category by id"/*, authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryResponse getCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        return new CategoryResponse(categoryDto);
    }

    @ApiOperation(value = "Add new category"/*,authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public CategoryResponse createCategory(
            @ApiParam(value = "Category", required = true)
            @RequestBody @Valid CategoryRequest request) {
        CategoryDto categoryDto = request.getCategoryDto();
        categoryDto = categoryService.create(categoryDto);
        return new CategoryResponse(categoryDto);
    }

    @ApiOperation(value = "Delete category by id" /* authorizations = {@Authorization(BASIC_AUTH)}*/)
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryResponse deleteCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id) {
        CategoryDto categoryDto = categoryService.deleteById(id);
        return new CategoryResponse(categoryDto);
    }

    @ApiOperation(value = "Update category" /* authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public CategoryResponse updateCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Category", required = true)
            @RequestBody @Valid CategoryRequest request) {
        CategoryDto categoryDto = request.getCategoryDto();
        categoryDto = categoryService.update(categoryDto, id);
        return new CategoryResponse(categoryDto);
    }
}
