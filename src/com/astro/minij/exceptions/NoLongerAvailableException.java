package com.astro.minij.exceptions;

public class NoLongerAvailableException extends IndexOutOfBoundsException {
    public NoLongerAvailableException(String s) {
        super("Requested " + s + ", but no more left.");
    }
}
