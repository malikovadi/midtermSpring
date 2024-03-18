package com.kg.alatoo.midtermSpring;

import com.kg.alatoo.midtermSpring.entities.Product;
import com.kg.alatoo.midtermSpring.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveAndFindProduct() {
        // Create a product entity
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(999.99);

        // Save the product entity
        productRepository.save(product);

        // Find the saved product entity
        Product foundProduct = productRepository.findById(product.getId()).orElse(null);

        // Assert that the found product is not null and has the correct properties
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getName()).isEqualTo("Laptop");
        assertThat(foundProduct.getPrice()).isEqualTo(999.99);
    }
}

