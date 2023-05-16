package ru.gb.storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.storage.entities.Order;
import ru.gb.storage.entities.Product;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
