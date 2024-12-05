package com.restaurant.repository;

import com.restaurant.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    boolean existsByName(String name);
    boolean existsByIdAndMenuItemsIsNotEmpty(Long id);
}