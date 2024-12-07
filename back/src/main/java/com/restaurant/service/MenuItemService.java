package com.restaurant.service;

import com.restaurant.dto.MenuItemDTO.MenuItemRequestDTO;
import com.restaurant.dto.MenuItemDTO.MenuItemResponseDTO;
import com.restaurant.entity.MenuCategory;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.entity.MenuItem;
import com.restaurant.repository.MenuCategoryRepository;
import com.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    public List<MenuItemResponseDTO> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return menuItems.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public MenuItemResponseDTO getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));
        return toResponseDTO(menuItem);
    }

    public MenuItemResponseDTO addMenuItem(MenuItemRequestDTO menuItemRequestDTO) {
        MenuCategory menuCategory = menuCategoryRepository.findById(menuItemRequestDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Menu category not found"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequestDTO.getName());
        menuItem.setPrice(menuItemRequestDTO.getPrice());
        menuItem.setDescription(menuItemRequestDTO.getDescription());
        menuItem.setCalorie(menuItemRequestDTO.getCalorie());
        menuItem.setWeight(menuItemRequestDTO.getWeight());
        menuItem.setPhoto(menuItemRequestDTO.getPhoto());
        menuItem.setStatus(menuItemRequestDTO.getStatus());
        menuItem.setMenuCategory(menuCategory);

        menuItem = menuItemRepository.save(menuItem);
        return toResponseDTO(menuItem);
    }

    public MenuItemResponseDTO updateMenuItem(Long id, MenuItemRequestDTO menuItemRequestDTO) {
        MenuCategory menuCategory = menuCategoryRepository.findById(menuItemRequestDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Menu category not found"));

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

        menuItem.setName(menuItemRequestDTO.getName());
        menuItem.setPrice(menuItemRequestDTO.getPrice());
        menuItem.setDescription(menuItemRequestDTO.getDescription());
        menuItem.setCalorie(menuItemRequestDTO.getCalorie());
        menuItem.setWeight(menuItemRequestDTO.getWeight());
        menuItem.setPhoto(menuItemRequestDTO.getPhoto());
        menuItem.setStatus(menuItemRequestDTO.getStatus());
        menuItem.setMenuCategory(menuCategory);

        menuItem = menuItemRepository.save(menuItem);
        return toResponseDTO(menuItem);
    }

    public void deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));
        menuItemRepository.delete(menuItem);
    }

    private MenuItemResponseDTO toResponseDTO(MenuItem menuItem) {
        MenuItemResponseDTO dto = new MenuItemResponseDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setCalorie(menuItem.getCalorie());
        dto.setWeight(menuItem.getWeight());
        dto.setPhoto(menuItem.getPhoto());
        dto.setStatus(menuItem.getStatus());
        dto.setCategoryName(menuItem.getMenuCategory().getName());
        return dto;
    }
}