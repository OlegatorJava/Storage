package ru.gb.service.cartservice.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.storage.api.ProductDto;
import ru.gb.storage.api.ResourceNotFoundExceptions;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id){
        ProductDto productDto = productServiceWebClient.get()
                .uri("/product/" + id)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundExceptions("Товар не найден, id: " + id)))
                .bodyToMono(ProductDto.class)
                .block();
        return productDto;
    }
}
