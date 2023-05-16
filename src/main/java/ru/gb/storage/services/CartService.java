package ru.gb.storage.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.storage.model.Cart;
import ru.gb.storage.entities.Product;
import ru.gb.storage.exceptions.ResourceNotFoundExceptions;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long id){
        Product product = productService.findById(id). orElseThrow(
                () -> new ResourceNotFoundExceptions("Продукт не может быть добавлен в корзину. " +
                        "Продукт не найден. ID продукта: " + id));
        tempCart.add(product);
    }

    public void changeQuantity(Long id, int delta){
        Product product = productService.findById(id). orElseThrow(
                () -> new ResourceNotFoundExceptions("Продукт не может быть добавлен в корзину. " +
                        "Продукт не найден. ID продукта: " + id));
        tempCart.changeQuantity(product,delta);
    }

    public void clearCart(){
        tempCart.clearCart();
    }

    public void removeProduct(Long id) {
        tempCart.removeProduct(id);
    }
}
