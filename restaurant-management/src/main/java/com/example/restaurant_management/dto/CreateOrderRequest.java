package com.example.restaurant_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private List<OrderItemRequest> items; // Списък с артикули за поръчката
}
