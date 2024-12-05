package com.restaurant.exception;

public class MenuCategoryNotFoundException extends RuntimeException {
    public MenuCategoryNotFoundException(Long id) {
        super("Menu category with ID " + id + " not found.");
    }
}