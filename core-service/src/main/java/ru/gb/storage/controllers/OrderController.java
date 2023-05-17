package ru.gb.storage.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.storage.api.OrderDto;

import ru.gb.storage.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrder(){
        log.info("Вернули заказ");
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id){
        log.info("Вернули заказ c " + id);
        return orderService.getOrder(id);
    }

    @GetMapping("/add")
    public void checkout(@RequestParam String username){
        log.info("Оформления заказа для пользователя " + username);
        orderService.checkout(username);
    }

}
