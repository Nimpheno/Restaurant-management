package com.example.restaurant_management.repositories;

import com.example.restaurant_management.entity.WarehouseItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseItemsRepository extends JpaRepository<WarehouseItems, Long> {
}
