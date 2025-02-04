package com.example.restaurant_management.controlers;

import com.example.restaurant_management.dto.CreateOrderRequest;
import com.example.restaurant_management.entity.Order;
import com.example.restaurant_management.entity.OrderItem;
import com.example.restaurant_management.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
    }

    @GetMapping("/turnover")
    public  ResponseEntity<BigDecimal> getDailyTurnover() {
        return ResponseEntity.ok(orderService.calculateDailyTurnover()); // Връща оборота за деня
    }
}
