package ru.gb.storage.entities;


import jakarta.persistence.*;
import lombok.Data;



import java.util.List;

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
    private List<Role> roles;
    @OneToMany(mappedBy="users", fetch=FetchType.LAZY)
    private List<OrderItem> orderItems;
    @OneToMany(mappedBy="users", fetch=FetchType.LAZY)
    private List<Order> orders;
}
