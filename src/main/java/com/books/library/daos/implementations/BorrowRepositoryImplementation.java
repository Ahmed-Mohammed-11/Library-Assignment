package com.books.library.daos.implementations;

import com.books.library.daos.repositories.BorrowRepository;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public class BorrowRepositoryImplementation implements BorrowRepository{

    private final JdbcTemplate jdbcTemplate;
    public BorrowRepositoryImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean isBookBorrowed(int bookId) {
        String sql = "SELECT COUNT(*) FROM Borrowing_Record WHERE book_id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }, bookId);
    }
    @Override
    @Transactional
    public void borrowBook(int bookId, int patronId) {
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);
        String sql = "INSERT INTO Borrowing_Record (book_id, patron_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, bookId, patronId, borrowDate, returnDate);
    }

    @Override
    @Transactional
    public void returnBook(int bookId, int patronId) {
        String sql = "DELETE FROM Borrowing_Record WHERE book_id = ? AND patron_id = ?";
        jdbcTemplate.update(sql, bookId, patronId);
    }
}
