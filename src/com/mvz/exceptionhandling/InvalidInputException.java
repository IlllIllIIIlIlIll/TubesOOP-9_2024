package com.mvz.exceptionhandling;

// Invalid input format
public class InvalidInputException extends java.lang.Exception{
    String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format("INVALID INPUT", message);
    }
}
