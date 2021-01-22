package com.astro.minij.exceptions;

public class InvalidNameException extends IllegalArgumentException {
    public InvalidNameException() {
        super();
    }

    public InvalidNameException(String s) {
        super(s);
    }
}
