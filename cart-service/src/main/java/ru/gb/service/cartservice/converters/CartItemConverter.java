package ru.gb.service.cartservice.converters;

import org.springframework.stereotype.Component;
import ru.gb.service.cartservice.model.Cart;
import ru.gb.service.cartservice.model.CartItem;
import ru.gb.storage.api.CartDto;
import ru.gb.storage.api.CartItemDto;
@Component
public class CartItemConverter {

    public CartItemDto modelToDto(CartItem cartItem){
        return new CartItemDto(
                cartItem.getId(),
                cartItem.getTitle(),
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
        );
    }
}
