package ru.gb.service.cartservice.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.service.cartservice.integration.ProductServiceIntegration;
import ru.gb.service.cartservice.model.Cart;
import ru.gb.storage.api.ProductDto;
import ru.gb.storage.api.ResourceNotFoundExceptions;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long id){
        ProductDto product = productServiceIntegration.getProductById(id). orElseThrow(
                () -> new ResourceNotFoundExceptions("Продукт не может быть добавлен в корзину. " +
                        "Продукт не найден. ID продукта: " + id));
        tempCart.add(product);
    }

    public void changeQuantity(Long id, int delta){
        ProductDto product = productServiceIntegration.getProductById(id). orElseThrow(
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
