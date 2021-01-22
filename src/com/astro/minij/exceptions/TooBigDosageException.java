package com.astro.minij.exceptions;

public class TooBigDosageException extends ArithmeticException {
    public TooBigDosageException() {
        super("During processing found dosage to be bigger than can be accepted.");
    }
}
