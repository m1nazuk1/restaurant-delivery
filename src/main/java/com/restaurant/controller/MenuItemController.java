package com.restaurant.controller;

import com.restaurant.dto.MenuItemDTO.MenuItemRequestDTO;
import com.restaurant.dto.MenuItemDTO.MenuItemResponseDTO;
import com.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponseDTO> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItemResponseDTO getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping
    public MenuItemResponseDTO addMenuItem(@RequestBody MenuItemRequestDTO menuItemRequestDTO) {
        return menuItemService.addMenuItem(menuItemRequestDTO);
    }

    @PutMapping("/{id}")
    public MenuItemResponseDTO updateMenuItem(@PathVariable Long id, @RequestBody MenuItemRequestDTO menuItemRequestDTO) {
        return menuItemService.updateMenuItem(id, menuItemRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}