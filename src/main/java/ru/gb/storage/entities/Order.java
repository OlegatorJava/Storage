package ru.gb.storage.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.storage.dto.UserDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="user_id")
    private User users;
    private int totalPrice;
    private String address;
    private String phone;


    @OneToMany(mappedBy="order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;


    public Order(User user, int totalPrice, String address, String phone) {
        this.users = user;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
    }

    public Order() {

    }
}
