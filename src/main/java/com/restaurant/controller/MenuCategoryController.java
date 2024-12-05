package com.restaurant.controller;

import com.restaurant.dto.MenuCategoryDTO.MenuCategoryRequestDTO;
import com.restaurant.dto.MenuCategoryDTO.MenuCategoryResponseDTO;
import com.restaurant.entity.MenuCategory;
import com.restaurant.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-categories")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping
    public List<MenuCategoryResponseDTO> getAllMenuCategories() {
        return menuCategoryService.getAllMenuCategories();
    }

    @GetMapping("/{id}")
    public MenuCategoryResponseDTO getMenuCategoryById(@PathVariable Long id) {
        return menuCategoryService.getMenuCategoryById(id);
    }

    @PostMapping
    public MenuCategoryResponseDTO addMenuCategory(@RequestBody MenuCategoryRequestDTO menuCategoryRequestDTO) {
        return menuCategoryService.addMenuCategory(menuCategoryRequestDTO);
    }

    @PutMapping("/{id}")
    public MenuCategoryResponseDTO updateMenuCategory(@PathVariable Long id, @RequestBody MenuCategoryRequestDTO menuCategoryRequestDTO) {
        return menuCategoryService.updateMenuCategory(id, menuCategoryRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuCategory(@PathVariable Long id) {
        MenuCategory menuCategory = menuCategoryService.getMenuCategoryByIdEntity(id);

        if (menuCategoryService.hasMenuItems(menuCategory)) {
            throw new IllegalStateException("Категория не может быть удалена, пока в ней есть блюда.");
        }

        menuCategoryService.deleteMenuCategory(id);
    }
}