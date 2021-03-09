package com.empire.products.domain.services;

import com.empire.products.domain.entities.Category;
import com.empire.products.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable(value = "getCategoriesCache")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
