package com.astro.minij.exceptions;

public class InvalidFirstAidKitSizeException extends IllegalArgumentException {
    public InvalidFirstAidKitSizeException(String s) {
        super("Expected first aid kit size s, m, l, xl (one of), but got: " + s);
    }
}
