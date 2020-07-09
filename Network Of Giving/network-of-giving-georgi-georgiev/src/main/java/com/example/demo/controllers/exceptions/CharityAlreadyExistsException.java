package com.example.demo.controllers.exceptions;

public class CharityAlreadyExistsException extends IllegalArgumentException {
    public CharityAlreadyExistsException() {
        super("text");
    }
}
