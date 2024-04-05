package com.kg.alatoo.midtermSpring.services;

import com.kg.alatoo.midtermSpring.dto.ProductDTO;

import java.util.List;

public interface ProductService {


    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO userDTO);
    ProductDTO updateProduct(Long id, ProductDTO userDTO);
    void deleteProduct(Long id);
    ProductDTO partiallyUpdateProduct(Long id, ProductDTO productDTO);
    List<ProductDTO> getProductsByOrderId(Long orderId);
}
