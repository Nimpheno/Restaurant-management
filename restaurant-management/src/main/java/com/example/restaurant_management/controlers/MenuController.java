package com.example.restaurant_management.controlers;

import com.example.restaurant_management.entity.MenuItem;
import com.example.restaurant_management.services.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")

public class MenuController {

    private final MenuItemService menuItemService;;

    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;

    }

    @GetMapping
    public List<MenuItem> getMenu() {
        return menuItemService.getAllMenuItems(); // Връща всички артикули от менюто
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemByID(@PathVariable Long id){
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping
    public MenuItem addNewItem(@RequestBody MenuItem menuItems){
        return menuItemService.addMenuItem(menuItems);
    }

    @PutMapping ("/{id}")
    public MenuItem updateItem(@PathVariable Long id, @RequestBody MenuItem updateItem){
        return menuItemService.updateMenuItem(id, updateItem);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}

