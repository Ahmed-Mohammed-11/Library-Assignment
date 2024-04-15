package com.books.library.exceptions.exceptions;

public class BookIsBorrowedException extends RuntimeException {
    public BookIsBorrowedException(String message) {
        super(message);
    }
}

