package com.kg.alatoo.midtermSpring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProducts() throws Exception {
        List<ProductDTO> productList = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");

        when(productService.getProductById(1L)).thenReturn(productDTO);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");

        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(productDTO);

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Product"));

        verify(productService, times(1)).createProduct(any(ProductDTO.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Updated Product");

        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(productDTO);

        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(productDTO);

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Product"));

        verify(productService, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(1L);
    }
}

