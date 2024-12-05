package com.restaurant.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(Long id) {
        super("Delivery with ID " + id + " not found.");
    }
}
