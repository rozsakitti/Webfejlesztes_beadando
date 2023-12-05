package com.example.pet;

public class PetNotFoundException extends Throwable {
    public PetNotFoundException(String message) {
        super(message);
    }
}
