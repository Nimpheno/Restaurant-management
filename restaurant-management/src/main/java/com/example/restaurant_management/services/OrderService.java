package com.example.restaurant_management.services;

import com.example.restaurant_management.dto.CreateOrderRequest;
import com.example.restaurant_management.dto.OrderItemRequest;
import com.example.restaurant_management.entity.MenuItem;
import com.example.restaurant_management.entity.Order;
import com.example.restaurant_management.entity.OrderItem;
import com.example.restaurant_management.repositories.MenuItemRepository;
import com.example.restaurant_management.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


////////Review the code before continuing/////////////////////
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    // 1. Създаване на нова поръчка
    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        // Проверка дали заявката съдържа поне един артикул
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Поръчката трябва да съдържа поне един артикул.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());

        // Обработка на всеки артикул в поръчката
        for (OrderItemRequest itemRequest : request.getItems()) {
            // Намиране на артикула в базата
            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Артикулът не съществува: " + itemRequest.getMenuItemId()));

            // Проверка дали артикулът е наличен
            if (!menuItem.isAvailable()) {
                throw new IllegalStateException("Артикулът не е наличен: " + menuItem.getName());
            }

            // Създаване на нов елемент в поръчката
            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        // Задаване на артикулите към поръчката и запис в базата
        order.setItems(orderItems);
        return orderRepository.save(order);
    }

    // 2. Анулиране на поръчка
    @Transactional
    public void cancelOrder(Long orderId) {
        // Проверка дали поръчката съществува в базата
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Поръчката не съществува: " + orderId));

        // Изтриване на поръчката
        orderRepository.delete(order);
    }

    // 3. Връщане на всички поръчки
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 4. Изчисляване на дневния оборот
    public double calculateDailyTurnover() {
        // Определяне на времевия интервал за текущия ден
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        // Намиране на всички поръчки за текущия ден и изчисляване на общата сума
        return orderRepository.findAllByCreatedAtBetween(startOfDay, endOfDay)
                .stream()
                .mapToDouble(order -> order.calculateTotalPrice().doubleValue())
                .sum();
    }
}