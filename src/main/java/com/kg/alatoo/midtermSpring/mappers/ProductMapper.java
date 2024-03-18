package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.entities.Product;
import org.mapstruct.*;

@Mapper
public interface ProductMapper {

    @Mapping(target = "orders", ignore = true) // Ignore mapping for orders (avoid cyclic dependency)
    Product productDtoToProduct(ProductDTO dto);

    @Mapping(target = "orders", ignore = true) // Ignore mapping for orders (avoid cyclic dependency)
    ProductDTO productToProductDto(Product product);
}