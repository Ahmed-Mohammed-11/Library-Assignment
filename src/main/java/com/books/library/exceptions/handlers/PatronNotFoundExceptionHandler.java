package com.books.library.exceptions.handlers;

import com.books.library.exceptions.exceptions.PatronNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatronNotFoundExceptionHandler {
    @ExceptionHandler(PatronNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(PatronNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


}
