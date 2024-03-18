package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.entities.Order;
import org.mapstruct.*;

@Mapper
public interface OrderMapper {
    @Mapping(target = "user", ignore = true) // Ignore mapping for user (avoid cyclic dependency)
    Order orderDtoToOrder(OrderDTO dto);

    @Mapping(target = "user", ignore = true) // Ignore mapping for user (avoid cyclic dependency)
    OrderDTO orderToOrderDto(Order order);
}
