package com.books.library.services;

import com.books.library.daos.implementations.BookRepositoryImplementation;
import com.books.library.daos.implementations.BorrowRepositoryImplementation;
import com.books.library.daos.implementations.PatronRepositoryImplementation;
import com.books.library.exceptions.exceptions.BookIsBorrowedException;
import com.books.library.exceptions.exceptions.BookIsNotBorrowedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import static com.books.library.constants.ResponseMessageConstants.*;

@Service
@AllArgsConstructor
public class BorrowService {

    private BorrowRepositoryImplementation borrowRepositoryImplementation;
    private BookRepositoryImplementation bookRepositoryImplementation;
    private PatronRepositoryImplementation patronRepositoryImplementation;

    public void borrowBook(int bookId, int patronId) {
        bookRepositoryImplementation.findById(bookId).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND));
        patronRepositoryImplementation.findById(patronId).orElseThrow(() -> new PatronNotFoundException(PATRON_NOT_FOUND));
        if(borrowRepositoryImplementation.isBookBorrowed(bookId))
            throw new BookIsBorrowedException(BOOK_IS_BORROWED);
        borrowRepositoryImplementation.borrowBook(bookId, patronId);
    }

    public void returnBook(int bookId, int patronId) {
        bookRepositoryImplementation.findById(bookId).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND));
        patronRepositoryImplementation.findById(patronId).orElseThrow(() -> new PatronNotFoundException(PATRON_NOT_FOUND));
        if(!borrowRepositoryImplementation.isBookBorrowed(bookId))
            throw new BookIsNotBorrowedException(BOOK_IS_NOT_BORROWED);
        borrowRepositoryImplementation.returnBook(bookId, patronId);
    }
}
