package com.saleservice.rest.controllers;

import com.saleservice.domain.entities.Category;
import com.saleservice.domain.services.CategoryService;
import com.saleservice.rest.models.requests.CategoryChangeRequest;
import com.saleservice.rest.models.requests.CategoryRequest;
import com.saleservice.rest.models.responses.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final String pathWithId = "/category/{id}";

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<Page<CategoryResponse>>getAll(@PageableDefault(page = 0, size = 10, sort = "description", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok().body(CategoryResponse.convert(categoryService.getAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse>save(@RequestBody @Valid CategoryRequest categoryRequest, UriComponentsBuilder uriComponentsBuilder){

        Category category = categoryService.save(categoryRequest);

        URI uri = uriComponentsBuilder.path(pathWithId).buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryResponse(category));
    }

    @PutMapping
    public ResponseEntity<CategoryResponse>change(@RequestBody @Valid CategoryChangeRequest categoryChangeRequest){

        Category category = categoryService.change(categoryChangeRequest);

        return ResponseEntity.ok().body(new CategoryResponse(category));
    }

}
