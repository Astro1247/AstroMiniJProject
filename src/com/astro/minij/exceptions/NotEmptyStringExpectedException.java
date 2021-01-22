package com.astro.minij.exceptions;

public class NotEmptyStringExpectedException extends InvalidNameException {
    public NotEmptyStringExpectedException() {
        super("Got empty string when expected not ampty one.");
    }
}
