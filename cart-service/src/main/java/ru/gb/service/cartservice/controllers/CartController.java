package ru.gb.service.cartservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.service.cartservice.converters.CartConverter;
import ru.gb.service.cartservice.model.Cart;
import ru.gb.service.cartservice.services.CartService;
import ru.gb.storage.api.CartDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Slf4j
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCart(){
        log.info("Вернули корзину");
        CartDto cartDto = cartConverter.modelToDto(cartService.getCurrentCart());
        return cartDto;
    }

    @GetMapping("/add/{id}")
    public void addProductInCart(@PathVariable Long id){
        log.info("Добавили в корзину продукт " + id);
        cartService.add(id);
    }

    @GetMapping("/change")
    public void changeQuantityProduct(@RequestParam("product_id") Long id, @RequestParam("delta") int delta){
        cartService.changeQuantity(id,delta);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clearCart();
    }

    @GetMapping("/remove_product/{id}")
    public void removeProductFromCart(@PathVariable Long id){
        cartService.removeProduct(id);
    }
}
