package ru.gb.storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.storage.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
