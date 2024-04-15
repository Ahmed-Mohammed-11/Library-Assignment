package com.books.library.exceptions.handlers;

import com.books.library.exceptions.exceptions.BookIsBorrowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class BookIsBorrowedExceptionHandler {
    @ExceptionHandler(BookIsBorrowedException.class)
    public ResponseEntity<String> handleBookIsBorrowed(BookIsBorrowedException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
