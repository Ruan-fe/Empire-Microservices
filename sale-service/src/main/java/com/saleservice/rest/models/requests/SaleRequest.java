package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Sale;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {


    private Long userId;
    private Double discount;
    private Double subTotal;
    private Double total;


    public Sale convert(){
        return Sale.builder().userId(userId).discount(discount).subTotal(subTotal).total(total).dateSale(LocalDateTime.now()).build();
    }
}
