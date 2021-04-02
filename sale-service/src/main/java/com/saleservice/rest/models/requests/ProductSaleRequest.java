package com.saleservice.rest.models.requests;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSaleRequest {

    private Long productId;
    private Long quantityProduct;

}
