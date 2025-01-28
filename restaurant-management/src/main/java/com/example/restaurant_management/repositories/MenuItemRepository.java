package com.example.restaurant_management.repositories;

import com.example.restaurant_management.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // Метод за намиране на всички налични артикули
    List<MenuItem> findByAvailableTrue();
}