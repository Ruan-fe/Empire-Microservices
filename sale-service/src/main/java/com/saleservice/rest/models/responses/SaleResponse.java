package com.saleservice.rest.models.responses;

import com.saleservice.domain.entities.Product;
import com.saleservice.domain.entities.Sale;
import lombok.*;
import org.springframework.data.domain.Page;

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



    public SaleResponse(Sale sale) {
        id = sale.getId();
        discount = sale.getDiscount();
        subTotal = sale.getSubTotal();
        total = sale.getTotal();
    }

    @Override
    public String toString() {
        return "Successful purchase, total value: " + total;
    }
}
