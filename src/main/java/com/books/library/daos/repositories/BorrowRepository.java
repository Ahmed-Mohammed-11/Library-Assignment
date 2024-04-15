package com.books.library.daos.repositories;

public interface BorrowRepository {
    Boolean isBookBorrowed(int bookId);
    void borrowBook(int bookId, int patronId);
    void returnBook(int bookId, int patronId);
}
