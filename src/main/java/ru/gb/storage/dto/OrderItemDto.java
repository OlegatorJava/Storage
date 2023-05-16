package ru.gb.storage.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.storage.entities.Order;
import ru.gb.storage.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int price ;

}
