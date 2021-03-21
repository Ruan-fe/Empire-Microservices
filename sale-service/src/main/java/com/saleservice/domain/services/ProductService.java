package com.saleservice.domain.services;

import com.saleservice.domain.entities.Product;
import com.saleservice.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

}
