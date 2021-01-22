package com.astro.minij.exceptions;

public class TooLongNameException extends InvalidNameException {
    public TooLongNameException() {
        super("Too long name provided. Try shorter one.");
    }
}
