package com.empire.products.rest.models.responses;

import com.empire.products.domain.entities.Category;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String description;

    public CategoryResponse(Category category) {
        id = category.getId();
        description = category.getDescription();
    }


    public static List<CategoryResponse> convert(List<Category> categories) {
        return categories.stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

}
