package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Product;
import com.saleservice.domain.entities.Sale;
import com.saleservice.domain.entities.SaleItem;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemRequest {

    private Long productId;
    private Long saleId;
    private Long quantityProductsSold;

    public SaleItem convert(Product product, Sale sale){
        return SaleItem.builder().product(product).sale(sale).quantityProductsSold(quantityProductsSold).build();
    }

}
