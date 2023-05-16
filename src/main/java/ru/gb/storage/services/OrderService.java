package ru.gb.storage.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.storage.converters.OrderMapper;
import ru.gb.storage.converters.ProductMapper;
import ru.gb.storage.converters.UserMapper;
import ru.gb.storage.dto.OrderDto;
import ru.gb.storage.dto.OrderItemDto;
import ru.gb.storage.dto.UserDto;
import ru.gb.storage.entities.Order;
import ru.gb.storage.entities.OrderItem;
import ru.gb.storage.entities.User;
import ru.gb.storage.exceptions.ResourceNotFoundExceptions;
import ru.gb.storage.model.Cart;
import ru.gb.storage.repositories.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public void checkout(String username) {
        Cart cart = cartService.getCurrentCart();
        User user = userService.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundExceptions("Пользователь не найден. Имя пользователя: " + username));

        Order order = new Order(user, cart.getTotalPrice(), "address", "928382");

        List<OrderItem> orderItemList = cart.getItems().stream().map(cartItem -> new OrderItem(
                user,
                order,
                cartItem.getTitle(),
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
        )).collect(Collectors.toList());

        order.setOrderItems(orderItemList);

        orderRepository.save(order);
    }

    public List<OrderDto> getAllOrder() {
        List<OrderDto> orderDtoList = orderRepository.findAll().stream().map(o -> new OrderDto(
                        o.getId(),
                        new UserDto(o.getUsers().getId(), o.getUsers().getName(), o.getUsers().getRoles()),
                        o.getTotalPrice(),
                        o.getAddress(),
                        o.getPhone(),
                        o.getOrderItems().stream().map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getTitle(),
                                orderItem.getQuantity(),
                                orderItem.getPricePerProduct(),
                                orderItem.getPrice()
                                )
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
        return orderDtoList;
    }

    public OrderDto getOrder(Long id) {
        OrderDto orderDto = orderRepository.findById(id).map(
                o -> new OrderDto(
                        o.getId(),
                        new UserDto(o.getUsers().getId(), o.getUsers().getName(), o.getUsers().getRoles()),
                        o.getTotalPrice(),
                        o.getAddress(),
                        o.getPhone(),
                        o.getOrderItems().stream().map(orderItem -> new OrderItemDto(
                                        orderItem.getId(),
                                        orderItem.getTitle(),
                                        orderItem.getQuantity(),
                                        orderItem.getPricePerProduct(),
                                        orderItem.getPrice()
                                )
                        ).collect(Collectors.toList())
                )
        ).orElseThrow(() -> new ResourceNotFoundExceptions("Заказ не найден, id:" + id));

        return orderDto;
    }
}
//UserDto user = userService.findByUsername(username).map(UserMapper.USER_MAPPER::fromUser).orElseThrow(
//                () -> new ResourceNotFoundExceptions("Пользователь не найден. Имя пользователя: " + username));
