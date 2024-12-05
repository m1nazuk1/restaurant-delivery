package com.restaurant.service;

import com.restaurant.dto.MenuCategoryDTO.MenuCategoryRequestDTO;
import com.restaurant.dto.MenuCategoryDTO.MenuCategoryResponseDTO;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.entity.MenuCategory;
import com.restaurant.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuCategoryService {

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    public List<MenuCategoryResponseDTO> getAllMenuCategories() {
        List<MenuCategory> categories = menuCategoryRepository.findAll();
        return categories.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public MenuCategoryResponseDTO getMenuCategoryById(Long id) {
        MenuCategory category = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu category not found"));
        return toResponseDTO(category);
    }

    public MenuCategory getMenuCategoryByIdEntity(Long id) {
        return menuCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu category not found"));
    }

    public MenuCategoryResponseDTO addMenuCategory(MenuCategoryRequestDTO menuCategoryRequestDTO) {
        MenuCategory category = new MenuCategory();
        category.setName(menuCategoryRequestDTO.getName());
        category.setDescription(menuCategoryRequestDTO.getDescription());

        category = menuCategoryRepository.save(category);
        return toResponseDTO(category);
    }

    public MenuCategoryResponseDTO updateMenuCategory(Long id, MenuCategoryRequestDTO menuCategoryRequestDTO) {
        MenuCategory category = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu category not found"));

        category.setName(menuCategoryRequestDTO.getName());
        category.setDescription(menuCategoryRequestDTO.getDescription());

        category = menuCategoryRepository.save(category);
        return toResponseDTO(category);
    }

    public void deleteMenuCategory(Long id) {
        MenuCategory category = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu category not found"));

        if (hasMenuItems(category)) {
            throw new IllegalStateException("Категория не может быть удалена, пока в ней есть блюда.");
        }

        menuCategoryRepository.delete(category);
    }

    public boolean hasMenuItems(MenuCategory category) {
        return !category.getMenuItems().isEmpty();
    }

    private MenuCategoryResponseDTO toResponseDTO(MenuCategory category) {
        MenuCategoryResponseDTO dto = new MenuCategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}