package com.example.DartsInventory.exceptions;

public class DartNotFoundException extends RuntimeException{
    public DartNotFoundException(String message) {
        super(message);
    }
}
