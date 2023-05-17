package ru.gb.storage.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private String title;
    private int quantity = 1;
    private int pricePerProduct;
    private int price ;



}
