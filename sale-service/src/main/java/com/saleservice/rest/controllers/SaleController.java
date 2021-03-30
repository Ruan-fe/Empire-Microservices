package com.saleservice.rest.controllers;

import com.saleservice.domain.services.SaleService;
import com.saleservice.rest.models.requests.ProductSaleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public List<ProductSaleRequest> makeSale(@RequestBody List<ProductSaleRequest> productSaleRequest){

        saleService.makeSale(productSaleRequest);
        return productSaleRequest;
    }



}
