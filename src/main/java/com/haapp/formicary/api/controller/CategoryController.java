package com.haapp.formicary.api.controller;

import com.haapp.formicary.api.message.category.CategoriesResponse;
import com.haapp.formicary.api.message.category.CategoryRequest;
import com.haapp.formicary.api.message.category.CategoryResponse;
import com.haapp.formicary.api.model.CategoryDto;
import com.haapp.formicary.domain.model.Category;
import com.haapp.formicary.domain.service.CategoryService;
import com.haapp.formicary.mapping.CategoryMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(
        value = "REST API for Category. API allows operations with category: create, read, update, delete."
        /*, authorizations = {@Authorization(BASIC_AUTH)}*/)
@RestController
@RequestMapping(value = "/api/v1/category", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @ApiOperation(value = "Select all categories"/*,
            authorizations = {@Authorization(BASIC_AUTH)}*/)
    @GetMapping("/")
    @ResponseStatus(OK)
    public CategoriesResponse getCategories() {
        List<Category> categories = categoryService.getAll();
        List<CategoryDto> categoriesDto = categories.stream().map(CategoryMapper.INSTANCE::categoryDomainToCategoryDto).collect(Collectors.toList());
        return new CategoriesResponse(categoriesDto);
    }

    @ApiOperation(value = "Find category by id")
//            authorizations = {@Authorization(BASIC_AUTH)})
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryResponse getCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new CategoryResponse(CategoryMapper.INSTANCE.categoryDomainToCategoryDto(category));
    }

    @ApiOperation(value = "Add new category"/*,
            authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PostMapping(name = "/", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public CategoryResponse createCategory(
            @ApiParam(value = "Category", required = true)
            @RequestBody @Valid CategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategoryDomain(request.getCategoryDto());
        category = categoryService.create(category);
        return new CategoryResponse(CategoryMapper.INSTANCE.categoryDomainToCategoryDto(category));
    }

    @ApiOperation(value = "Delete category by id"
           /* authorizations = {@Authorization(BASIC_AUTH)}*/)
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryResponse deleteCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id) {
        Category category = categoryService.deleteById(id);
        return new CategoryResponse(CategoryMapper.INSTANCE.categoryDomainToCategoryDto(category));
    }

    @ApiOperation(value = "Update category"
           /* authorizations = {@Authorization(BASIC_AUTH)}*/)
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public CategoryResponse updateCategory(
            @ApiParam(value = "Category ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Category", required = true)
            @RequestBody @Valid CategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategoryDomain(request.getCategoryDto());
        category = categoryService.update(category, id);
        return new CategoryResponse(CategoryMapper.INSTANCE.categoryDomainToCategoryDto(category));
    }
}
