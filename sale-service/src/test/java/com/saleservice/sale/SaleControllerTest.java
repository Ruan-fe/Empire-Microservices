package com.saleservice.sale;

import com.saleservice.helper.MockMvcHelper;
import com.saleservice.rest.models.requests.ProductSaleRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "classpath:scenarios/data.sql")
@Sql(value = "classpath:scenarios/clean-all.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class SaleControllerTest {

    private final String path = "/sale";

    @Autowired
    private MockMvcHelper mockMvcHelper;

    @Test
    public void shouldReturnOkWhenRealizeSale() throws Exception {

        List<ProductSaleRequest> productSaleRequests = new ArrayList<>();

        ProductSaleRequest sale1 = ProductSaleRequest.builder().productId(1L).quantityProduct(2L).build();
        ProductSaleRequest sale2 = ProductSaleRequest.builder().productId(2L).quantityProduct(3L).build();

        productSaleRequests.add(sale1);
        productSaleRequests.add(sale2);


        mockMvcHelper.save(path,productSaleRequests)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestWhenQuantityProductToSaleIsInvalid() throws Exception {

        List<ProductSaleRequest> productSaleRequests = new ArrayList<>();

        ProductSaleRequest sale1 = ProductSaleRequest.builder().productId(1L).quantityProduct(0L).build();

        productSaleRequests.add(sale1);


        mockMvcHelper.save(path,productSaleRequests)
                .andExpect(status().isBadRequest());
    }

}
