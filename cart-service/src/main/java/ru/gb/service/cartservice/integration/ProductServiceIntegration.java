package ru.gb.service.cartservice.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.storage.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8099/app/product/" + id, ProductDto.class));
    }
}
