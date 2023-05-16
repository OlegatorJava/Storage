package ru.gb.storage.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.storage.entities.Order;
import ru.gb.storage.entities.OrderItem;
import ru.gb.storage.entities.Role;

import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private List<Role> roles;

}
