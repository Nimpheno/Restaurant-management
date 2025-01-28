package com.example.restaurant_management.repositories;

import com.example.restaurant_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Намиране на всички поръчки в определен времеви интервал
    List<Order> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
