package ru.gb.storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.storage.entities.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByName(String name);

        @Query("SELECT u.id FROM User u WHERE u.name = ?1")
        Long findIdByName(String username);
}
