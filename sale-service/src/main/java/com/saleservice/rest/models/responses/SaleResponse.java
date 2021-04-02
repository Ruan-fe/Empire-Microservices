package com.saleservice.rest.models.responses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleResponse {


    private Long id;
    private Double discount;
    private Double subTotal;
    private Double total;
    private LocalDateTime dateSale = LocalDateTime.now();


    @Override
    public String toString() {
        return "Successful purchase, total value: " + total;
    }
}
