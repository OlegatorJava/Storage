package ru.gb.storage.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int price;

}
