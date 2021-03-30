package com.saleservice.rest.models.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleRequest {

    private Long productId;
    private Long quantityProduct;

}
