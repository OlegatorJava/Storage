package ru.gb.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long id;
    private String title;
    private int quantity = 1;
    private int pricePerProduct;
    private int price ;

    public CartItem(Long id, String title, int pricePerProduct, int price) {
        this.id = id;
        this.title = title;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
    public void recalculatePrice(){
        this.price = pricePerProduct * quantity;
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        recalculatePrice();
    }

}
