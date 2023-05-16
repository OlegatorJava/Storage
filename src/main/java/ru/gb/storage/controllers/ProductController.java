package ru.gb.storage.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.storage.converters.ProductMapper;
import ru.gb.storage.dto.ProductDto;
import ru.gb.storage.entities.Product;
import ru.gb.storage.exceptions.ResourceNotFoundExceptions;
import ru.gb.storage.services.ProductService;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProducts(){
        log.info("Метод работает!");
        return ProductMapper.MAPPER.fromListProducts(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        return productService.findById(id).map(ProductMapper.MAPPER::fromProduct).orElseThrow(
                () -> new ResourceNotFoundExceptions("Продукт не найден, id:" + id));
    }
}
