package com.mvz.exceptionhandling;

// Tile been occupied by other plant or !isAquatic, vice versa
public class InvalidTileException extends Exception {
    public InvalidTileException() {
        super();
    }

    public InvalidTileException(String message) {
        super(message);
    }
}