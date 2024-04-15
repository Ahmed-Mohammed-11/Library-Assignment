package com.books.library.exceptions.exceptions;

public class BookIsNotBorrowedException extends RuntimeException{
    public BookIsNotBorrowedException(String message) {
        super(message);
    }
}
