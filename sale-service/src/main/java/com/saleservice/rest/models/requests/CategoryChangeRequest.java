package com.saleservice.rest.models.requests;

import com.saleservice.domain.entities.Category;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryChangeRequest {

    @NotNull(message = "Description cannot be null!")
    private String description;

    public Category convert(Long id){
        return Category.builder().id(id).description(description).build();
    }
}
