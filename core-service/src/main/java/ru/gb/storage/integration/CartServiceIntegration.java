package ru.gb.storage.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.storage.api.CartDto;
import ru.gb.storage.api.ProductDto;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public CartDto getCurrentCart(){
        return restTemplate.getForObject("http://localhost:8090/app/cart" , CartDto.class);
    }
}
