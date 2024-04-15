package com.books.library.daos.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.books.library.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookRepositoryImplementation.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookRepositoryImplementationTest {
    @Autowired
    private BookRepositoryImplementation bookRepositoryImplementation;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    void testInsert() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), isA(Object[].class))).thenReturn(1);

        // Act
        Integer actualInsertResult = bookRepositoryImplementation.insert(new Book(1, "Dr", "JaneDoe", 1, "Isbn"));

        // Assert
        verify(jdbcTemplate).update(eq("INSERT INTO Book (title, author, publication_year, ISBN) VALUES (?, ?, ?, ?)"),
                isA(Object[].class));
        assertEquals(1, actualInsertResult.intValue());
    }

    @Test
    void testFindById() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.query(Mockito.<String>any(), Mockito.<RowMapper<Object>>any(), isA(Object[].class)))
                .thenReturn(new ArrayList<>());

        // Act
        Optional<Book> actualFindByIdResult = bookRepositoryImplementation.findById(1);

        // Assert
        verify(jdbcTemplate).query(eq("SELECT * FROM Book WHERE id = ?"), isA(RowMapper.class), isA(Object[].class));
        assertFalse(actualFindByIdResult.isPresent());
    }

    @Test
    void testFindAll() throws DataAccessException {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query(Mockito.<String>any(), Mockito.<RowMapper<Object>>any())).thenReturn(objectList);

        // Act
        List<Book> actualFindAllResult = bookRepositoryImplementation.findAll();

        // Assert
        verify(jdbcTemplate).query(eq("SELECT * FROM Book"), isA(RowMapper.class));
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(objectList, actualFindAllResult);
    }

    @Test
    void testUpdate() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), isA(Object[].class))).thenReturn(1);

        // Act
        bookRepositoryImplementation.update(new Book(1, "Dr", "JaneDoe", 1, "Isbn"));

        // Assert
        verify(jdbcTemplate).update(
                eq("UPDATE Book SET title = ?, author = ?, publication_year = ?, ISBN = ? WHERE id = ?"), isA(Object[].class));
    }

    @Test
    void testDeleteById() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), isA(Object[].class))).thenReturn(1);

        // Act
        bookRepositoryImplementation.deleteById(1);

        // Assert
        verify(jdbcTemplate).update(eq("DELETE FROM Book WHERE id = ?"), isA(Object[].class));
    }
}
