package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.ProductDTO;
import com.kg.alatoo.midtermSpring.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(source = "order.id", target = "orderId")
    ProductDTO productToProductDto(Product product);

    @Mapping(source = "orderId", target = "order.id")
    Product productDtoToProduct(ProductDTO dto);
}
