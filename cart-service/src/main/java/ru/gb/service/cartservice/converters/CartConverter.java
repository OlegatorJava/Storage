package ru.gb.service.cartservice.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.service.cartservice.model.Cart;
import ru.gb.storage.api.CartDto;

import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto modelToDto(Cart cart){
        return new CartDto(
                cart.getTotalPrice(),
                cart.getItems().stream().map(cartItemConverter::modelToDto).collect(Collectors.toList())
        );
    }
}
