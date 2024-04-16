package com.books.library.daos.implementations;

import com.books.library.daos.repositories.BookRepository;
import com.books.library.entities.Book;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImplementation implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Integer insert(Book entity) {
        String sql = "INSERT INTO Book (title, author, publication_year, isbn) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getTitle(), entity.getAuthor(), entity.getPublicationYear(), entity.getIsbn());
    }

    @Override
    public Optional<Book> findById(Integer id) {
        String sql = "SELECT * FROM Book WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id).stream().findFirst();

    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM Book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    @Transactional
    public void update(Book entity) {
        String sql = "UPDATE Book SET title = ?, author = ?, publication_year = ?, isbn = ? WHERE id = ?";
        jdbcTemplate.update(sql, entity.getTitle(), entity.getAuthor(), entity.getPublicationYear(), entity.getIsbn(), entity.getId());
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        String sql = "DELETE FROM Book WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
