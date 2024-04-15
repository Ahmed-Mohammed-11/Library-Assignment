package com.books.library.exceptions.exceptions;

public class PatronNotFoundException extends RuntimeException {
    public PatronNotFoundException(String message) {
        super(message);
    }
}
