//package com.example.restaurant_management.controlers;
//
//import com.example.restaurant_management.entity.Order;
//import com.example.restaurant_management.entity.OrderItem;
//import com.example.restaurant_management.services.OrderService;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    private final OrderService orderService;
//
//    public OrderController(OrderService orderService)
//    {
//        this.orderService = orderService;
//    }
//
//    @PostMapping
//    public Order createOrder(@RequestBody List<OrderItem> items) {
//        return orderService.createOrder(items); // Създава нова поръчка
//    }
//
//    @GetMapping("/turnover")
//    public BigDecimal getDailyTurnover() {
//        return orderService.getDailyTurnover(); // Връща оборота за деня
//    }
//}
