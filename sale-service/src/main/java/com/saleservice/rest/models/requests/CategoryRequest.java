package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    @NotNull(message = "Description cannot be null!")
    private String description;

    public Category convert(){
        return Category.builder().description(description).build();
    }
}
