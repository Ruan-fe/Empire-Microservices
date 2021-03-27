package com.saleservice.rest.models.responses;

import com.saleservice.domain.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


@Getter
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String description;
    private Double value;
    private Long quantityStock;
    private CategoryResponse category;


    public ProductResponse(Product product) {
        id = product.getId();
        description = product.getDescription();
        value = product.getValue();
        quantityStock = product.getQuantityStock();
        category = new CategoryResponse(product.getCategory());
    }

    public static Page<ProductResponse> convert(Page<Product> products) {
        return products.map(ProductResponse::new);
    }
}
