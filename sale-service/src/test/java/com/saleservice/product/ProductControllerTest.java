package com.saleservice.product;

import com.saleservice.helper.MockMvcHelper;
import com.saleservice.rest.models.requests.ProductChangeRequest;
import com.saleservice.rest.models.requests.ProductRequest;
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
public class ProductControllerTest {

    private final String path = "/sale/product";

    @Autowired
    private MockMvcHelper mockMvcHelper;


    @Test
    public void shouldReturnOkWhenSearchToProducts() throws Exception {

        mockMvcHelper.get(path)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description").value("GTX 1050 TI"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].value").value(800))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].quantityStock").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].category.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].description").value("GTX 750 TI"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].value").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].quantityStock").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].category.id").value(1));
    }

    @Test
    public void shouldReturnCreatedWhenInsertAProduct() throws Exception {

        ProductRequest productRequest = ProductRequest.builder()
                .description("GTX 650 TI")
                .value(450.0)
                .quantityStock(20L)
                .categoryId(1L)
                .build();

        mockMvcHelper.save(path,productRequest)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("GTX 650 TI"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value(450))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantityStock").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.id").value(1));
    }

    @Test
    public void shouldReturnOkWhenUpdateAProduct() throws Exception {

        Long productId = 1L;

        ProductChangeRequest productChangeRequest = ProductChangeRequest.builder()
                .description("GTX 750 TI")
                .value(980.0)
                .quantityStock(6L)
                .categoryId(1L)
                .build();

        mockMvcHelper.put(path,productId, productChangeRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("GTX 750 TI"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value(980.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantityStock").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.id").value(1));
    }




}
