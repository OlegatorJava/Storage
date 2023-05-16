package ru.gb.storage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import ru.gb.storage.dto.OrderDto;

import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String password;
    @ManyToMany
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Collection<Role> roles;
    @OneToMany(mappedBy="users", fetch=FetchType.LAZY)
    private Collection<OrderItem> orderItems;
    @OneToMany(mappedBy="users", fetch=FetchType.LAZY)
    private Collection<Order> orders;
}
