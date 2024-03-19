package com.kg.alatoo.midtermSpring.mappers;

import com.kg.alatoo.midtermSpring.dto.OrderDTO;
import com.kg.alatoo.midtermSpring.entities.Order;
import org.mapstruct.*;

@Mapper
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderDTO orderToOrderDto(Order order);

    @Mapping(source = "userId", target = "user.id")
    Order orderDtoToOrder(OrderDTO dto);
}
