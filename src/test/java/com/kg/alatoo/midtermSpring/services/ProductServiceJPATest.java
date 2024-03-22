package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.entities.Product;
import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.repositories.ProductRepository;
import com.kg.alatoo.midtermSpring.mappers.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceJPATest {

    @Autowired
    private ProductServiceJPA productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    @Test
    void testGetProductById() {
        // Mocking repository behavior
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(10.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Mocking mapper behavior
        ProductDTO expectedProductDTO = new ProductDTO();
        expectedProductDTO.setId(1L);
        expectedProductDTO.setName("Test Product");
        expectedProductDTO.setPrice(10.0);
        when(productMapper.productToProductDto(product)).thenReturn(expectedProductDTO);

        // Calling service method
        ProductDTO actualProductDTO = productService.getProductById(1L);

        // Verifying result
        assertEquals(expectedProductDTO.getId(), actualProductDTO.getId());
        assertEquals(expectedProductDTO.getName(), actualProductDTO.getName());
        assertEquals(expectedProductDTO.getPrice(), actualProductDTO.getPrice());
    }
}
