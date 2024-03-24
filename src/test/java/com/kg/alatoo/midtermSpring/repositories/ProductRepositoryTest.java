package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(10.0, savedProduct.getPrice());
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);

        Product savedProduct = productRepository.save(product);
        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());
        assertTrue(optionalProduct.isPresent());
        assertEquals("Test Product", optionalProduct.get().getName());
        assertEquals(10.0, optionalProduct.get().getPrice());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);

        Product savedProduct = productRepository.save(product);
        savedProduct.setPrice(15.0);

        Product updatedProduct = productRepository.save(savedProduct);
        assertEquals(savedProduct.getId(), updatedProduct.getId());
        assertEquals("Test Product", updatedProduct.getName());
        assertEquals(15.0, updatedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);

        Product savedProduct = productRepository.save(product);
        productRepository.delete(savedProduct);

        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());
        assertFalse(optionalProduct.isPresent());
    }
}
