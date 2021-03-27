package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import com.saleservice.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductChangeRequest {

    private Long id;
    private String description;
    private Double value;
    private Long quantityStock;
    private Long categoryId;

    public Product convert(Category category){
        return Product.builder().id(id).description(description).value(value).quantityStock(quantityStock).category(category).build();
    }
}
