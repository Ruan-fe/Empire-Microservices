package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryChangeRequest {

    private String description;

    public Category convert(Long id){
        return Category.builder().id(id).description(description).build();
    }
}
