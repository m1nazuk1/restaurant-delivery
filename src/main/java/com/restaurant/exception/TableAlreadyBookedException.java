package com.restaurant.exception;

public class TableAlreadyBookedException extends RuntimeException {
    public TableAlreadyBookedException(Long tableId) {
        super("Table with ID " + tableId + " is already booked.");
    }
}