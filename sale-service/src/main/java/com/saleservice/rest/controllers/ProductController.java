package com.saleservice.rest.controllers;

import com.saleservice.domain.entities.Product;
import com.saleservice.domain.services.ProductService;
import com.saleservice.rest.models.requests.ProductChangeRequest;
import com.saleservice.rest.models.requests.ProductRequest;
import com.saleservice.rest.models.responses.ProductResponse;
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
@RequestMapping("sale/product")
public class ProductController {

    private final String pathWithId = "sale/product/{id}";

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>>getAllProducts(@PageableDefault(page = 0, size = 10, sort = "description", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> products = productService.getAll(pageable);
        return ResponseEntity.ok().body(ProductResponse.convert(products));
    }

    @PostMapping
    public ResponseEntity<ProductResponse>save(@RequestBody @Valid ProductRequest productRequest, UriComponentsBuilder uriComponentsBuilder){

        Product product = productService.save(productRequest);

        URI uri = uriComponentsBuilder.path(pathWithId).buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse>change(@RequestBody ProductChangeRequest productChangeRequest, @PathVariable("id") Long id){

       Product product = productService.change(productChangeRequest, id);

       return ResponseEntity.ok().body(new ProductResponse(product));

    }
}
