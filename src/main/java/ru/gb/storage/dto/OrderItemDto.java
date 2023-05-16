package ru.gb.storage.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.storage.entities.Order;
import ru.gb.storage.entities.User;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(Long id, String title, int quantity, int pricePerProduct, int price) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}
