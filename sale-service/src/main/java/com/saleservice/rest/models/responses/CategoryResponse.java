package com.saleservice.rest.models.responses;

import com.saleservice.domain.entities.Category;
import lombok.*;
import org.springframework.data.domain.Page;



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


    public static Page<CategoryResponse> convert(Page<Category> categories) {
        return categories.map(CategoryResponse::new);
    }

}
