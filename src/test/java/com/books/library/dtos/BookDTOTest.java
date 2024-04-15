package com.books.library.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookDTO.class})
@ExtendWith(SpringExtension.class)
class BookDTOTest {
    @Autowired
    private BookDTO bookDTO;

    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(bookDTO.canEqual("Other"));
        assertTrue(bookDTO.canEqual(bookDTO));
        assertTrue(bookDTO.canEqual(new BookDTO(mock(BookDTO.BookDTOBuilder.class))));
    }

    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new BookDTO("Dr", "JaneDoe", 1, "Isbn"), null);
        assertNotEquals(new BookDTO("Dr", "JaneDoe", 1, "Isbn"), "Different type to BookDTO");
    }

    @Test
    void testEquals2() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Mr", "JaneDoe", 1, "Isbn");

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEquals3() {
        // Arrange
        BookDTO bookDTO = new BookDTO(null, "JaneDoe", 1, "Isbn");

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEquals4() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "Dr", 1, "Isbn");

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEquals5() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", null, 1, "Isbn");

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEquals6() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 3, "Isbn");

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEquals7() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, "Dr");

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEquals8() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, null);

        // Act and Assert
        assertNotEquals(bookDTO, new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, "Isbn");

        // Act and Assert
        assertEquals(bookDTO, bookDTO);
        int expectedHashCodeResult = bookDTO.hashCode();
        assertEquals(expectedHashCodeResult, bookDTO.hashCode());
    }

    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, "Isbn");
        BookDTO bookDTO2 = new BookDTO("Dr", "JaneDoe", 1, "Isbn");

        // Act and Assert
        assertEquals(bookDTO, bookDTO2);
        int expectedHashCodeResult = bookDTO.hashCode();
        assertEquals(expectedHashCodeResult, bookDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        BookDTO bookDTO = new BookDTO(null, "JaneDoe", 1, "Isbn");
        BookDTO bookDTO2 = new BookDTO(null, "JaneDoe", 1, "Isbn");

        // Act and Assert
        assertEquals(bookDTO, bookDTO2);
        int expectedHashCodeResult = bookDTO.hashCode();
        assertEquals(expectedHashCodeResult, bookDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", null, 1, "Isbn");
        BookDTO bookDTO2 = new BookDTO("Dr", null, 1, "Isbn");

        // Act and Assert
        assertEquals(bookDTO, bookDTO2);
        int expectedHashCodeResult = bookDTO.hashCode();
        assertEquals(expectedHashCodeResult, bookDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCode5() {
        // Arrange
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, null);
        BookDTO bookDTO2 = new BookDTO("Dr", "JaneDoe", 1, null);

        // Act and Assert
        assertEquals(bookDTO, bookDTO2);
        int expectedHashCodeResult = bookDTO.hashCode();
        assertEquals(expectedHashCodeResult, bookDTO2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        BookDTO actualBookDTO = new BookDTO();
        actualBookDTO.setAuthor("JaneDoe");
        actualBookDTO.setIsbn("Isbn");
        actualBookDTO.setPublicationYear(1);
        actualBookDTO.setTitle("Dr");
        String actualToStringResult = actualBookDTO.toString();
        String actualAuthor = actualBookDTO.getAuthor();
        String actualIsbn = actualBookDTO.getIsbn();
        int actualPublicationYear = actualBookDTO.getPublicationYear();

        // Assert that nothing has changed
        assertEquals("BookDTO(title=Dr, author=JaneDoe, publicationYear=1, isbn=Isbn)", actualToStringResult);
        assertEquals("Dr", actualBookDTO.getTitle());
        assertEquals("Isbn", actualIsbn);
        assertEquals("JaneDoe", actualAuthor);
        assertEquals(1, actualPublicationYear);
    }

    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        BookDTO actualBookDTO = new BookDTO("Dr", "JaneDoe", 1, "Isbn");
        actualBookDTO.setAuthor("JaneDoe");
        actualBookDTO.setIsbn("Isbn");
        actualBookDTO.setPublicationYear(1);
        actualBookDTO.setTitle("Dr");
        String actualToStringResult = actualBookDTO.toString();
        String actualAuthor = actualBookDTO.getAuthor();
        String actualIsbn = actualBookDTO.getIsbn();
        int actualPublicationYear = actualBookDTO.getPublicationYear();

        // Assert that nothing has changed
        assertEquals("BookDTO(title=Dr, author=JaneDoe, publicationYear=1, isbn=Isbn)", actualToStringResult);
        assertEquals("Dr", actualBookDTO.getTitle());
        assertEquals("Isbn", actualIsbn);
        assertEquals("JaneDoe", actualAuthor);
        assertEquals(1, actualPublicationYear);
    }

    @Test
    void testNewBookDTO() {
        // Arrange and Act
        BookDTO actualBookDTO = new BookDTO(mock(BookDTO.BookDTOBuilder.class));

        // Assert
        assertNull(actualBookDTO.getAuthor());
        assertNull(actualBookDTO.getIsbn());
        assertNull(actualBookDTO.getTitle());
        assertEquals(0, actualBookDTO.getPublicationYear());
    }
}
