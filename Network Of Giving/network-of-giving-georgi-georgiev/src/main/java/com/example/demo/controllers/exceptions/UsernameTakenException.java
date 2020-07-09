package com.example.demo.controllers.exceptions;

public class UsernameTakenException extends Exception {
    public UsernameTakenException(String text) {
        super(text);
    }
}
