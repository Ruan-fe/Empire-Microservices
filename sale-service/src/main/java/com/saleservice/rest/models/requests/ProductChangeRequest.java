package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import com.saleservice.domain.entities.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductChangeRequest {

    private String description;
    private Double value;
    private Long quantityStock;
    private Long categoryId;

    public Product convert(Category category, Long productId){
        return Product.builder().id(productId).description(description).value(value).quantityStock(quantityStock).category(category).build();
    }
}
