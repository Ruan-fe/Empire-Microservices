package com.empire.products.rest.models.responses;

import com.empire.products.domain.entities.Category;
import com.empire.products.domain.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String description;
    private Double value;
    private Long stock;
    private Category category;


    public ProductResponse(Product product) {
        id = product.getId();
        description = product.getDescription();
        value = product.getValue();
        stock = product.getStock();
        category = product.getCategory();
    }

    public static Page<ProductResponse> convert(Page<Product> products) {
        return products.map(ProductResponse::new);
    }
}
