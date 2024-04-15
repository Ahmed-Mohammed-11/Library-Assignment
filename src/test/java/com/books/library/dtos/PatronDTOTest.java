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

@ContextConfiguration(classes = {PatronDTO.class})
@ExtendWith(SpringExtension.class)
class PatronDTOTest {
    @Autowired
    private PatronDTO patronDTO;

    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse(patronDTO.canEqual("Other"));
        assertTrue(patronDTO.canEqual(patronDTO));
        assertTrue(patronDTO.canEqual(new PatronDTO(mock(PatronDTO.PatronDTOBuilder.class))));
    }

    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new PatronDTO("Name", "Contact Information"), null);
        assertNotEquals(new PatronDTO("Name", "Contact Information"), "Different type to PatronDTO");
    }

    @Test
    void testEquals2() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO("Contact Information", "Contact Information");

        // Act and Assert
        assertNotEquals(patronDTO, new PatronDTO("Name", "Contact Information"));
    }

    @Test
    void testEquals3() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO(null, "Contact Information");

        // Act and Assert
        assertNotEquals(patronDTO, new PatronDTO("Name", "Contact Information"));
    }

    @Test
    void testEquals4() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO("Name", "Name");

        // Act and Assert
        assertNotEquals(patronDTO, new PatronDTO("Name", "Contact Information"));
    }

    @Test
    void testEquals5() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO("Name", null);

        // Act and Assert
        assertNotEquals(patronDTO, new PatronDTO("Name", "Contact Information"));
    }

    @Test
    void testEquals6() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO(mock(PatronDTO.PatronDTOBuilder.class));

        // Act and Assert
        assertNotEquals(patronDTO, new PatronDTO("Name", "Contact Information"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO("Name", "Contact Information");

        // Act and Assert
        assertEquals(patronDTO, patronDTO);
        int expectedHashCodeResult = patronDTO.hashCode();
        assertEquals(expectedHashCodeResult, patronDTO.hashCode());
    }

    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO("Name", "Contact Information");
        PatronDTO patronDTO2 = new PatronDTO("Name", "Contact Information");

        // Act and Assert
        assertEquals(patronDTO, patronDTO2);
        int expectedHashCodeResult = patronDTO.hashCode();
        assertEquals(expectedHashCodeResult, patronDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO(null, "Contact Information");
        PatronDTO patronDTO2 = new PatronDTO(null, "Contact Information");

        // Act and Assert
        assertEquals(patronDTO, patronDTO2);
        int expectedHashCodeResult = patronDTO.hashCode();
        assertEquals(expectedHashCodeResult, patronDTO2.hashCode());
    }

    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO("Name", null);
        PatronDTO patronDTO2 = new PatronDTO("Name", null);

        // Act and Assert
        assertEquals(patronDTO, patronDTO2);
        int expectedHashCodeResult = patronDTO.hashCode();
        assertEquals(expectedHashCodeResult, patronDTO2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        PatronDTO actualPatronDTO = new PatronDTO();
        actualPatronDTO.setContactInformation("Contact Information");
        actualPatronDTO.setName("Name");
        String actualToStringResult = actualPatronDTO.toString();
        String actualContactInformation = actualPatronDTO.getContactInformation();

        // Assert that nothing has changed
        assertEquals("Contact Information", actualContactInformation);
        assertEquals("Name", actualPatronDTO.getName());
        assertEquals("PatronDTO(name=Name, contactInformation=Contact Information)", actualToStringResult);
    }

    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        PatronDTO actualPatronDTO = new PatronDTO("Name", "Contact Information");
        actualPatronDTO.setContactInformation("Contact Information");
        actualPatronDTO.setName("Name");
        String actualToStringResult = actualPatronDTO.toString();
        String actualContactInformation = actualPatronDTO.getContactInformation();

        // Assert that nothing has changed
        assertEquals("Contact Information", actualContactInformation);
        assertEquals("Name", actualPatronDTO.getName());
        assertEquals("PatronDTO(name=Name, contactInformation=Contact Information)", actualToStringResult);
    }

    @Test
    void testNewPatronDTO() {
        // Arrange and Act
        PatronDTO actualPatronDTO = new PatronDTO(mock(PatronDTO.PatronDTOBuilder.class));

        // Assert
        assertNull(actualPatronDTO.getContactInformation());
        assertNull(actualPatronDTO.getName());
    }
}
