package com.example.restaurant_management.services;

import com.example.restaurant_management.entity.MenuItem;
import com.example.restaurant_management.repositories.MenuItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
    
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem addMenuItem(MenuItem menuItems) {
        return menuItemRepository.save(menuItems);
    }

    public void deleteMenuItem(Long id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
        } else {
            throw new RuntimeException("MenuItem with ID " + id + " not found.");
        }
    }

    public MenuItem updateMenuItem(Long id, MenuItem updatedMenuItem) {
        Optional<MenuItem> existingMenuItem = menuItemRepository.findById(id);
        if (existingMenuItem.isPresent()) {
            MenuItem menuItem = existingMenuItem.get();
            menuItem.setName(updatedMenuItem.getName());
            menuItem.setPrice(updatedMenuItem.getPrice());
            menuItem.setAvailable(updatedMenuItem.isAvailable());
            return menuItemRepository.save(menuItem);
        } else {
            throw new RuntimeException("MenuItem with ID " + id + " not found.");
        }

    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem with ID " + id + " not found."));
    }

}
