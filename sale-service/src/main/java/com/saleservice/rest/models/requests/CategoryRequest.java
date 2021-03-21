package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private String description;

    public Category convert(){
        return Category.builder().description(description).build();
    }
}
