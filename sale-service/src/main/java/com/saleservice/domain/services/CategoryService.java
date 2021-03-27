package com.saleservice.domain.services;


import com.saleservice.domain.entities.Category;
import com.saleservice.domain.repositories.CategoryRepository;
import com.saleservice.rest.models.requests.CategoryChangeRequest;
import com.saleservice.rest.models.requests.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category save(CategoryRequest categoryRequest) {
        Category category = categoryRequest.convert();
        return categoryRepository.save(category);
    }

    public Category change(CategoryChangeRequest categoryChangeRequest, Long id) {
        Category category = categoryChangeRequest.convert(id);
        return categoryRepository.save(category);
    }
}
