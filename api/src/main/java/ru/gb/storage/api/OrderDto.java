package ru.gb.storage.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
