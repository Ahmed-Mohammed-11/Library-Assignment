package com.books.library.controllers;

import com.books.library.services.BorrowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.books.library.constants.ApiConstants.API_BORROW;
import static com.books.library.constants.ApiConstants.API_RETURN;
import static com.books.library.constants.ResponseMessageConstants.BORROWED_BOOKS;
import static com.books.library.constants.ResponseMessageConstants.RETURNED_BOOKS;

@RestController
@AllArgsConstructor
public class BorrowControllers {

    private BorrowService borrowService;
    @PostMapping(API_BORROW + "/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable int bookId, @PathVariable int patronId) {
        borrowService.borrowBook(bookId, patronId);
        return ResponseEntity.status(HttpStatus.OK).body(BORROWED_BOOKS);
    }

    @PutMapping(API_RETURN + "/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable int bookId, @PathVariable int patronId) {
        borrowService.returnBook(bookId, patronId);
        return ResponseEntity.status(HttpStatus.OK).body(RETURNED_BOOKS);
    }
}
