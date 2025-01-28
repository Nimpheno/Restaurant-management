package com.example.restaurant_management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private Long menuItemId; // ID на артикула от менюто
    private int quantity;    // Количество
}

