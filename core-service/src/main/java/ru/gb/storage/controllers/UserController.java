package ru.gb.storage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.storage.api.OrderDto;

import ru.gb.storage.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public List<OrderDto> findAllOrdersByUserId(@PathVariable Long id){
        return userService.findAllOrdersByUserId(id);
    }
}
