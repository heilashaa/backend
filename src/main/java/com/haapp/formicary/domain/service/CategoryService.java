package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Category;
import com.haapp.formicary.infrastructure.exception.NotFoundException;
import com.haapp.formicary.mapping.CategoryMapper;
import com.haapp.formicary.persistence.model.CategoryEntity;
import com.haapp.formicary.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.haapp.formicary.infrastructure.exception.ErrorMessage.CATEGORY_NOT_FOUND;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {

    final private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        List<CategoryEntity> categoryEntities= categoryRepository.findAll();
        return categoryEntities.stream().map(CategoryMapper.INSTANCE::categoryPersistenceToCategoryDomain)
                .collect(Collectors.toList());
    }

    public Category findById(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        return CategoryMapper.INSTANCE.categoryPersistenceToCategoryDomain(categoryEntity);
    }

    public Category create(Category category){
        if(nonNull(category)) {
            CategoryEntity categoryEntity = categoryRepository.save(
                    CategoryMapper.INSTANCE.categoryDomainToCategoryPersistence(category)
            );
            return CategoryMapper.INSTANCE.categoryPersistenceToCategoryDomain(categoryEntity);
        }
        return null;
    }

    public Category deleteById(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        categoryRepository.delete(categoryEntity);
        return CategoryMapper.INSTANCE.categoryPersistenceToCategoryDomain(categoryEntity);
    }

    public Category update(Category category, Long id){
        categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        CategoryEntity categoryEntity = CategoryMapper.INSTANCE.categoryDomainToCategoryPersistence(category);
        categoryEntity.setId(id);
        categoryEntity = categoryRepository.save(categoryEntity);
        return CategoryMapper.INSTANCE.categoryPersistenceToCategoryDomain(categoryEntity);
    }
}
