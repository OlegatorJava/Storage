package ru.gb.service.cartservice.model;

import lombok.Data;
import ru.gb.storage.api.ProductDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
            for (CartItem item : items) {
                if (item.getId().equals(product.getId())) {
                    item.changeQuantity(1);
                    recalculate();
                    return;
                }
            }
            items.add(new CartItem(product.getId(), product.getTitle(), product.getPrice(), product.getPrice()));

    recalculate();
}

    public void changeQuantity(ProductDto product, int delta){
        for (CartItem item: items){
            if (item.getId().equals(product.getId())) {
                item.changeQuantity(delta);
                if(item.getQuantity() == 0){
                    removeProduct(item.getId());
                }
                recalculate();
                return;
            }
        }

    }

    public void clearCart(){
        items.clear();
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void removeProduct(Long id) {
        if (!items.isEmpty()) {
            for (CartItem item : items) {
                if (item.getId().equals(id)) {
                    items.remove(item);
                    recalculate();
                    return;
                }
            }

    }

    }
}
