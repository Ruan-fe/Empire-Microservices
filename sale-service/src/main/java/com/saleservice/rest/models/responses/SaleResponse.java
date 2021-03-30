package com.saleservice.rest.models.responses;

import com.saleservice.domain.entities.Product;
import com.saleservice.domain.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {


    private Long id;
    private Double discount;
    private Double subTotal;
    private Double total;
    private LocalDateTime dateSale;



    public SaleResponse(Sale sale) {
        id = sale.getId();
        discount = sale.getDiscount();
        subTotal = sale.getSubTotal();
        total = sale.getTotal();
    }

    public static Page<SaleResponse> convert(Page<Sale> sales) {
        return sales.map(SaleResponse::new);
    }
}
