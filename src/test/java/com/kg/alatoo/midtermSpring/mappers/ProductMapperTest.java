package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.entities.Order;
import com.kg.alatoo.midtermSpring.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProductDtoToProduct() {
        // Given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(10.0);
        productDTO.setOrderId(456L);

        // When
        Product product = productMapper.productDtoToProduct(productDTO);

        // Then
        assertNotNull(product);
        assertEquals("Test Product", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(456L, product.getOrder().getId());
    }

    @Test
    public void testProductToProductDto() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        Order order = new Order();
        order.setId(456L);
        product.setOrder(order);

        // When
        ProductDTO productDTO = productMapper.productToProductDto(product);

        // Then
        assertNotNull(productDTO);
        assertEquals("Test Product", productDTO.getName());
        assertEquals(10.0, productDTO.getPrice());
        assertEquals(456L, productDTO.getOrderId());
    }
}
