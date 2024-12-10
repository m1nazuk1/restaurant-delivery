package com.restaurant.exception;

public class VisitorNotFoundException extends RuntimeException {
    public VisitorNotFoundException(Long id) {
        super("Visitor with ID " + id + " not found.");
    }
}