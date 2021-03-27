package com.saleservice.category;

import com.saleservice.helper.MockMvcHelper;
import com.saleservice.rest.models.requests.CategoryChangeRequest;
import com.saleservice.rest.models.requests.CategoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "classpath:scenarios/data.sql")
@Sql(value = "classpath:scenarios/clean-all.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class CategoryControllerTest {

    private final String path = "/sale/category";

    @Autowired
    private MockMvcHelper mockMvcHelper;

    @Test
    public void shouldReturnOkWhenSearchToCategories() throws Exception {

        mockMvcHelper.get(path)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description").value("Placa de video"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].description").value("Processador"));
    }

    @Test
    public void shouldReturnCreatedWhenInsertACategory() throws Exception {

        CategoryRequest categoryRequest = CategoryRequest.builder()
                .description("Fonte")
                .build();

        mockMvcHelper.save(path,categoryRequest)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Fonte"));
    }

    @Test
    public void shouldReturnOkWhenUpdateACategory() throws Exception {

        Long categoryId = 2L;

        CategoryChangeRequest categoryChangeRequest = CategoryChangeRequest.builder()
                .description("Placa mae")
                .build();

        mockMvcHelper.put(path,categoryId, categoryChangeRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Placa mae"));
    }


}
