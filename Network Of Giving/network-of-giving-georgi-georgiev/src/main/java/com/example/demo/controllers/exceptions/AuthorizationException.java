package com.example.demo.controllers.exceptions;

public class AuthorizationException extends Exception {
    public AuthorizationException(String s) {
        super(s);
    }

    public AuthorizationException() {
        super("You must be logged in.");
    }
}
