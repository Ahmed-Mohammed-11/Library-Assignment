package com.books.library.daos.implementations;

import com.books.library.daos.repositories.PatronRepository;
import com.books.library.entities.Patron;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatronRepositoryImplementation implements PatronRepository {

    private final JdbcTemplate jdbcTemplate;
    public PatronRepositoryImplementation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Integer insert(Patron entity) {
        String sql = "INSERT INTO Patron (name, contact_information) VALUES (?, ?)";
        return jdbcTemplate.update(sql, entity.getName(), entity.getContactInformation());
    }

    @Override
    public Optional<Patron> findById(Integer id) {
        String sql = "SELECT * FROM Patron WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Patron.class), id).stream().findFirst();
    }

    @Override
    public List<Patron> findAll() {
        String sql = "SELECT * FROM Patron";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Patron.class));
    }

    @Override
    @Transactional
    public void update(Patron entity) {
        String sql = "UPDATE Patron SET name = ?, contact_information = ? WHERE id = ?";
        jdbcTemplate.update(sql, entity.getName(), entity.getContactInformation(), entity.getId());
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        String sql = "DELETE FROM Patron WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
