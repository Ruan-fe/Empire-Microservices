package com.saleservice.domain.services;

import com.saleservice.configurations.exceptions.ProductException;
import com.saleservice.configurations.exceptions.ResourceNotFoundException;
import com.saleservice.configurations.exceptions.SaleException;
import com.saleservice.domain.entities.Product;
import com.saleservice.domain.entities.Sale;
import com.saleservice.domain.entities.User;
import com.saleservice.domain.feignclients.UserFeignClient;
import com.saleservice.domain.repositories.ProductRepository;
import com.saleservice.domain.repositories.SaleItemRepository;
import com.saleservice.domain.repositories.SaleRepository;
import com.saleservice.rest.models.requests.ProductSaleRequest;

import com.saleservice.rest.models.requests.SaleItemRequest;
import com.saleservice.rest.models.requests.SaleRequest;
import com.saleservice.rest.models.responses.SaleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaleService {


    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Transactional
    public SaleResponse makeSale(List<ProductSaleRequest> productSaleRequest, String bearerToken) {


         Long userId = searchAuthenticatedUser(bearerToken);

         Double totalValue = calculateTotalValueAndVerifyIfHaveInStock(productSaleRequest);

         Long saleId = insertInSale(totalValue, userId);

         insertInSaleItemAndReduceStock(saleId, productSaleRequest);

         SaleResponse saleResponse = SaleResponse.builder().total(totalValue).build();

         return saleResponse;


    }

    private Long searchAuthenticatedUser(String bearerToken) {

        Long userId = null;

        try {
            User user = userFeignClient.getAuthenticated(bearerToken);
            userId = user.getId();
        }
        catch (Exception e){
            throw new SaleException(e.getMessage());
        }

        return userId;

    }


    private Double calculateTotalValueAndVerifyIfHaveInStock(List<ProductSaleRequest> productSaleRequest) {

        Double totalValue = 0.0;

        for(ProductSaleRequest productSale: productSaleRequest){

            Long productId = productSale.getProductId();
            Long quantityProduct = productSale.getQuantityProduct();

            Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found!"));

            verifyIfHaveInStock(product, quantityProduct);

            totalValue += product.getValue() * quantityProduct;

        }

        return totalValue;
    }

    private Long insertInSale(Double totalValue, Long userId) {
        SaleRequest saleRequest = SaleRequest.builder().userId(userId).discount(0.0).subTotal(totalValue).total(totalValue).build();
        Sale sale = saleRepository.save(saleRequest.convert());
        Long saleId = sale.getId();
        return saleId;
    }

    private void insertInSaleItemAndReduceStock(Long saleId, List<ProductSaleRequest> productSaleRequest) {

        for(ProductSaleRequest productSale: productSaleRequest){

            Long productId = productSale.getProductId();
            Long quantityProduct = productSale.getQuantityProduct();

            SaleItemRequest saleItemRequest = SaleItemRequest.builder().saleId(saleId).productId(productId).quantityProductsSold(quantityProduct).build();

            Product product = productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found!"));
            Sale sale = saleRepository.findById(saleId).orElseThrow(()-> new ResourceNotFoundException("Sale not found!"));

            saleItemRepository.save(saleItemRequest.convert(product,sale));

            reduceStock(product,quantityProduct);
        }
    }

    private void reduceStock(Product product, Long quantityProduct) {

        Long stock = product.getQuantityStock() - quantityProduct;

        product.setQuantityStock(stock);

        productRepository.save(product);

    }

    private void verifyIfHaveInStock(Product product, Long quantityProduct){
        Long stockProduct = product.getQuantityStock();

        if(quantityProduct <= 0){
            throw new ProductException("Quantity of " + product.getDescription() + " cannot be 0 or less than 0");
        }

        if(stockProduct <= 0 || stockProduct - quantityProduct < 0 ){
            throw new ProductException("Product " + product.getDescription() + " stock is insufficient, have just " + product.getQuantityStock() + " in stock!");
        }
    }

}
