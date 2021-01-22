package com.astro.minij.exceptions;

public class TooLowDosageException extends IllegalArgumentException {
    public TooLowDosageException() {
        super("Too low dosage. Minimum allowed dosage is 0.1");
    }
}
