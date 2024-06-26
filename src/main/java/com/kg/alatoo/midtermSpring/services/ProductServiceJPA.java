package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.exceptions.NotFoundException;
import com.kg.alatoo.midtermSpring.entities.Product;
import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.repositories.ProductRepository;
import com.kg.alatoo.midtermSpring.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceJPA implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceJPA(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return productMapper.productToProductDto(product);
    }

    @Override
    public List <ProductDTO> getProductsByOrderId(Long orderId){
        List<Product> products = productRepository.findByOrderId(orderId);
        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.productDtoToProduct(productDTO);
        product = productRepository.save(product);
        return productMapper.productToProductDto(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new NotFoundException("Couldn't update product with id: " + id);
        }
        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        // Update other fields as needed
        existingProduct = productRepository.save(existingProduct);
        return productMapper.productToProductDto(existingProduct);
    }

    @Override
    public ProductDTO partiallyUpdateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new NotFoundException("Couldn't update product with id: " + id);
        }

        // Apply partial updates
        if (productDTO.getName() != null) {
            existingProduct.setName(productDTO.getName());
        }
        if (productDTO.getPrice() != 0) {
            existingProduct.setPrice(productDTO.getPrice());
        }
        // Add more fields to update as needed

        existingProduct = productRepository.save(existingProduct);
        return productMapper.productToProductDto(existingProduct);
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
