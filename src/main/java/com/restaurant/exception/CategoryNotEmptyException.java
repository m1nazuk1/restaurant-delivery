package com.restaurant.exception;

public class CategoryNotEmptyException extends RuntimeException {
    public CategoryNotEmptyException(String name) {
        super("Category " + name + " is not empty and cannot be deleted.");
    }
}