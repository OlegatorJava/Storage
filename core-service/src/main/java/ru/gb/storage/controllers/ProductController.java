package ru.gb.storage.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.storage.api.ProductDto;
import ru.gb.storage.api.ResourceNotFoundExceptions;
import ru.gb.storage.converters.ProductMapper;
import ru.gb.storage.services.ProductService;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
@CrossOrigin("*")
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
