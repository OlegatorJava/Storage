package ru.gb.storage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.storage.api.OrderDto;

import ru.gb.storage.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public List<OrderDto> findAllOrdersByUserId(@PathVariable Long id){
        return userService.findAllOrdersByUserId(id);
    }
}
