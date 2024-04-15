package com.books.library.controllers;

import com.books.library.dtos.BookDTO;
import com.books.library.services.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.books.library.constants.ApiConstants.API_BOOKS;
import static com.books.library.constants.ResponseMessageConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping(API_BOOKS)
public class BookControllers {

    private BookService bookService;
    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        Integer bookId = id;
        BookDTO book = bookService.getBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO book) {
        bookService.insertBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(BOOK_ADDED_SUCCESSFULLY);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @Valid @RequestBody BookDTO book) {
        Integer bookId = id;
        bookService.updateBook(bookId, book);
        return ResponseEntity.status(HttpStatus.OK).body(BOOK_UPDATED_SUCCESSFULLY);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        Integer bookId = id;
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(BOOK_DELETED_SUCCESSFULLY);
    }
}
