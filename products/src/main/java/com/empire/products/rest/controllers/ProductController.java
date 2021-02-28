package com.empire.products.rest.controllers;

import com.empire.products.domain.entities.Product;
import com.empire.products.domain.services.ProductService;
import com.empire.products.rest.models.responses.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable){
        Page<Product> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok().body(ProductResponse.convert(products));    }

}
