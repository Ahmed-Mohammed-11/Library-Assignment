package com.books.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.books.library.daos.implementations.PatronRepositoryImplementation;
import com.books.library.daos.mappers.PatronMapper;
import com.books.library.dtos.PatronDTO;
import com.books.library.entities.Patron;
import com.books.library.exceptions.exceptions.PatronNotFoundException;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatronService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PatronServiceTest {
  @MockBean
  private PatronMapper patronMapper;

  @MockBean
  private PatronRepositoryImplementation patronRepositoryImplementation;

  @Autowired
  private PatronService patronService;

  @Test
  void testGetPatronById() {
    // Arrange
    Patron buildResult = Patron.builder().contactInformation("Contact Information").id(1).name("Name").build();
    Optional<Patron> ofResult = Optional.of(buildResult);
    when(patronRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    when(patronMapper.toPatronDTO(Mockito.<Patron>any())).thenThrow(new PatronNotFoundException("An error occurred"));

    // Act and Assert
    assertThrows(PatronNotFoundException.class, () -> patronService.getPatronById(1));
    verify(patronRepositoryImplementation).findById(eq(1));
    verify(patronMapper).toPatronDTO(isA(Patron.class));
  }

  @Test
  void testGetPatronById2() {
    // Arrange
    Optional<Patron> emptyResult = Optional.empty();
    when(patronRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(PatronNotFoundException.class, () -> patronService.getPatronById(1));
    verify(patronRepositoryImplementation).findById(eq(1));
  }

  @Test
  void testInsertPatron() {
    // Arrange
    when(patronRepositoryImplementation.insert(Mockito.<Patron>any())).thenReturn(1);
    Patron buildResult = Patron.builder().contactInformation("Contact Information").id(1).name("Name").build();
    when(patronMapper.toPatron(Mockito.<PatronDTO>any())).thenReturn(buildResult);
    PatronDTO patron = new PatronDTO("Name", "Contact Information");

    // Act
    patronService.insertPatron(patron);

    // Assert that nothing has changed
    verify(patronRepositoryImplementation).insert(isA(Patron.class));
    verify(patronMapper).toPatron(isA(PatronDTO.class));
    assertEquals("Contact Information", patron.getContactInformation());
    assertEquals("Name", patron.getName());
  }

  @Test
  void testInsertPatron2() {
    // Arrange
    when(patronMapper.toPatron(Mockito.<PatronDTO>any())).thenThrow(new PatronNotFoundException("An error occurred"));

    // Act and Assert
    assertThrows(PatronNotFoundException.class,
            () -> patronService.insertPatron(new PatronDTO("Name", "Contact Information")));
    verify(patronMapper).toPatron(isA(PatronDTO.class));
  }

  @Test
  void testUpdatePatron() {
    // Arrange
    doNothing().when(patronRepositoryImplementation).update(Mockito.<Patron>any());
    Patron buildResult = Patron.builder().contactInformation("Contact Information").id(1).name("Name").build();
    Optional<Patron> ofResult = Optional.of(buildResult);
    when(patronRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    Patron buildResult2 = Patron.builder().contactInformation("Contact Information").id(1).name("Name").build();
    when(patronMapper.toPatron(Mockito.<PatronDTO>any())).thenReturn(buildResult2);

    // Act
    patronService.updatePatron(1, new PatronDTO("Name", "Contact Information"));

    // Assert
    verify(patronRepositoryImplementation).findById(eq(1));
    verify(patronRepositoryImplementation).update(isA(Patron.class));
    verify(patronMapper).toPatron(isA(PatronDTO.class));
  }

  @Test
  void testUpdatePatron2() {
    // Arrange
    doThrow(new PatronNotFoundException("An error occurred")).when(patronRepositoryImplementation)
            .update(Mockito.<Patron>any());
    Patron buildResult = Patron.builder().contactInformation("Contact Information").id(1).name("Name").build();
    Optional<Patron> ofResult = Optional.of(buildResult);
    when(patronRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    Patron buildResult2 = Patron.builder().contactInformation("Contact Information").id(1).name("Name").build();
    when(patronMapper.toPatron(Mockito.<PatronDTO>any())).thenReturn(buildResult2);

    // Act and Assert
    assertThrows(PatronNotFoundException.class,
            () -> patronService.updatePatron(1, new PatronDTO("Name", "Contact Information")));
    verify(patronRepositoryImplementation).findById(eq(1));
    verify(patronRepositoryImplementation).update(isA(Patron.class));
    verify(patronMapper).toPatron(isA(PatronDTO.class));
  }

  @Test
  void testUpdatePatron3() {
    // Arrange
    Optional<Patron> emptyResult = Optional.empty();
    when(patronRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(PatronNotFoundException.class,
            () -> patronService.updatePatron(1, new PatronDTO("Name", "Contact Information")));
    verify(patronRepositoryImplementation).findById(eq(1));
  }
}
