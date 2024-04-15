package com.books.library.daos.mappers;

import com.books.library.dtos.BookDTO;
import com.books.library.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMap {
    public Book toBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .publicationYear(bookDTO.getPublicationYear())
                .isbn(bookDTO.getIsbn())
                .build();
    }

    public BookDTO toBookDTO(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .isbn(book.getIsbn())
                .build();
    }
}


