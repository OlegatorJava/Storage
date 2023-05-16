package ru.gb.storage.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.storage.entities.OrderItem;
import ru.gb.storage.entities.User;

import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private UserDto users;
    private int totalPrice;
    private String address;
    private String phone;
    private List<OrderItemDto> orderItems;

}
