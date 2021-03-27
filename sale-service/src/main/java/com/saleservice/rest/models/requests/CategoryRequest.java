package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    private String description;

    public Category convert(){
        return Category.builder().description(description).build();
    }
}
