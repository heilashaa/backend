package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Category;
import com.haapp.formicary.persistence.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {

    final private CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<Category> getAll(){
        var categories= categoryRepository.findAll();
        return asList(modelMapper.map(categories, Category[].class));
    }
}
