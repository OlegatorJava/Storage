package ru.gb.storage.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.storage.model.Cart;
import ru.gb.storage.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Slf4j
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCart(){
        log.info("Вернули корзину");
        return cartService.getCurrentCart();
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
