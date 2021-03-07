package com.empire.products.rest.controllers;


import com.empire.products.domain.services.CategoryService;
import com.empire.products.rest.models.responses.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponse>>getAllCategories(){
        return ResponseEntity.ok().body(CategoryResponse.convert(categoryService.getAllCategories()));
    }

}
