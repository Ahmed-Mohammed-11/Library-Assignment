package com.books.library.services;

import com.books.library.daos.implementations.BookRepositoryImplementation;
import com.books.library.daos.mappers.BookMap;
import com.books.library.dtos.BookDTO;
import com.books.library.entities.Book;
import com.books.library.exceptions.exceptions.BookNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.books.library.constants.ResponseMessageConstants.BOOK_NOT_FOUND;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepositoryImplementation bookRepositoryImplementation;
    private BookMap bookMapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepositoryImplementation.findAll();
        if (books.isEmpty())
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        return books.stream().map(bookMapper::toBookDTO).toList();
    }

    public BookDTO getBookById(Integer bookId) {
        Book book = bookRepositoryImplementation.findById(bookId).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND));
        return bookMapper.toBookDTO(book);
    }

    public void insertBook(BookDTO book) {
        Book bookEntity = bookMapper.toBook(book);
        bookRepositoryImplementation.insert(bookEntity);
    }

    public void updateBook(Integer bookId, BookDTO book) {
        bookRepositoryImplementation.findById(bookId).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND));
        Book bookEntity = bookMapper.toBook(book);
        bookEntity.setId(bookId);
        bookRepositoryImplementation.update(bookEntity);
    }

    public void deleteBook(Integer bookId) {
        bookRepositoryImplementation.findById(bookId).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND));
        bookRepositoryImplementation.deleteById(bookId);
    }
}
