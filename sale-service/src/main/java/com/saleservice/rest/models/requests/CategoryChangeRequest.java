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
public class CategoryChangeRequest {

    private String description;

    public Category convert(Long id){
        return Category.builder().id(id).description(description).build();
    }
}
