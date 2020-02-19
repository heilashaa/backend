package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.CategoryDto;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.mapping.CategoryMapper;
import com.haapp.formicary.persistence.model.Category;
import com.haapp.formicary.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.CATEGORY_NOT_FOUND;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {

    final private CategoryRepository categoryRepository;

    public List<CategoryDto> getAll(){
        List<Category> categories= categoryRepository.findAll();
        return categories.stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }

    public CategoryDto create(CategoryDto categoryDto){
        if(nonNull(categoryDto)) {
            Category category = categoryRepository.save(
                    CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto)
            );
            return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
        }
        return null;
    }

    public CategoryDto deleteById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }

    public CategoryDto update(CategoryDto categoryDto, Long id){
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        category.setId(id);
        category = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }
}
