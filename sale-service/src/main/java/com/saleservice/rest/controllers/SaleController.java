package com.saleservice.rest.controllers;

import com.saleservice.domain.services.SaleService;
import com.saleservice.rest.models.requests.ProductSaleRequest;
import com.saleservice.rest.models.responses.SaleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<?> makeSale(@RequestBody List<ProductSaleRequest> productSaleRequest){

        SaleResponse saleResponse = saleService.makeSale(productSaleRequest);

        return ResponseEntity.ok(saleResponse.toString());
    }



}
