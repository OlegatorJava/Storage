package ru.gb.storage.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="user_id")
    private User users;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="order_id")
    private Order order;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int price ;

    public OrderItem(User users, Order order, String title, int quantity, int pricePerProduct, int price) {
        this.users = users;
        this.order = order;
        this.title = title;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}
