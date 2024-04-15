package com.books.library.daos.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.books.library.entities.Patron;

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

@ContextConfiguration(classes = {PatronRepositoryImplementation.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PatronRepositoryImplementationTest {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PatronRepositoryImplementation patronRepositoryImplementation;

    @Test
    void testInsert() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), isA(Object[].class))).thenReturn(1);

        // Act
        Integer actualInsertResult = patronRepositoryImplementation.insert(new Patron(1, "Name", "Contact Information"));

        // Assert
        verify(jdbcTemplate).update(eq("INSERT INTO Patron (name, contact_information) VALUES (?, ?)"),
                isA(Object[].class));
        assertEquals(1, actualInsertResult.intValue());
    }

    @Test
    void testFindAll() throws DataAccessException {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<>();
        when(jdbcTemplate.query(Mockito.<String>any(), Mockito.<RowMapper<Object>>any())).thenReturn(objectList);

        // Act
        List<Patron> actualFindAllResult = patronRepositoryImplementation.findAll();

        // Assert
        verify(jdbcTemplate).query(eq("SELECT * FROM Patron"), isA(RowMapper.class));
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(objectList, actualFindAllResult);
    }

    @Test
    void testUpdate() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), isA(Object[].class))).thenReturn(1);

        // Act
        patronRepositoryImplementation.update(new Patron(1, "Name", "Contact Information"));

        // Assert
        verify(jdbcTemplate).update(eq("UPDATE Patron SET name = ?, contact_information = ? WHERE id = ?"),
                isA(Object[].class));
    }

    @Test
    void testDeleteById() throws DataAccessException {
        // Arrange
        when(jdbcTemplate.update(Mockito.<String>any(), isA(Object[].class))).thenReturn(1);

        // Act
        patronRepositoryImplementation.deleteById(1);

        // Assert
        verify(jdbcTemplate).update(eq("DELETE FROM Patron WHERE id = ?"), isA(Object[].class));
    }
}
