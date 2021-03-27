package com.saleservice.domain.services;

import com.saleservice.domain.entities.Category;
import com.saleservice.domain.entities.Product;
import com.saleservice.domain.repositories.CategoryRepository;
import com.saleservice.domain.repositories.ProductRepository;
import com.saleservice.rest.models.requests.ProductChangeRequest;
import com.saleservice.rest.models.requests.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product save(ProductRequest productRequest) {

        Category category = categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(()-> new RuntimeException());

        Product product = productRequest.convert(category);

        return productRepository.save(product);
    }

    public Product change(ProductChangeRequest productChangeRequest) {
        Product product = productChangeRequest.convert();
        return  productRepository.save(product);

    }
}
