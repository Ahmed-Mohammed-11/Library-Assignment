package com.books.library.exceptions.handlers;

import com.books.library.exceptions.exceptions.BookIsNotBorrowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookIsNotBorrowedExceptionHandler {
    @ExceptionHandler(BookIsNotBorrowedException.class)
    public ResponseEntity<String> handleBookIsNotBorrowed(BookIsNotBorrowedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
