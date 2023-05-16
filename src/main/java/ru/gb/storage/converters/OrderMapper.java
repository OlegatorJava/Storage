package ru.gb.storage.converters;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gb.storage.dto.OrderDto;
import ru.gb.storage.dto.ProductDto;
import ru.gb.storage.entities.Order;
import ru.gb.storage.entities.Product;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    Order toOrder(OrderDto orderDto);

    @InheritInverseConfiguration
    OrderDto fromOrder(Order order);

    List<Order> toListOrders(List<OrderDto> orderDtoList);

    List<OrderDto> fromListOrders(List<Order> ordersList);
}
