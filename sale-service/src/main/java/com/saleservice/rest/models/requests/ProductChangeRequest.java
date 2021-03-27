package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import com.saleservice.domain.entities.Product;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductChangeRequest {

    @NotNull(message = "Description cannot be null!")
    private String description;
    @NotNull(message = "Value cannot be null!")
    private Double value;
    @NotNull(message = "Quantity stock cannot be null!")
    private Long quantityStock;
    @NotNull(message = "Category cannot be null!")
    private Long categoryId;

    public Product convert(Category category, Long productId){
        return Product.builder().id(productId).description(description).value(value).quantityStock(quantityStock).category(category).build();
    }
}
