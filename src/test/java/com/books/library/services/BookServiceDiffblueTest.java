package com.books.library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.books.library.daos.implementations.BookRepositoryImplementation;
import com.books.library.daos.mappers.BookMap;
import com.books.library.dtos.BookDTO;
import com.books.library.entities.Book;
import com.books.library.exceptions.exceptions.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookServiceDiffblueTest {
    @MockBean
    private BookMap bookMap;

    @MockBean
    private BookRepositoryImplementation bookRepositoryImplementation;

    @Autowired
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        // Arrange
        when(bookRepositoryImplementation.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getAllBooks());
        verify(bookRepositoryImplementation).findAll();
    }

    @Test
    void testGetAllBooks2() {
        // Arrange
        ArrayList<Book> bookList = new ArrayList<>();
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        bookList.add(buildResult);
        when(bookRepositoryImplementation.findAll()).thenReturn(bookList);
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, "Isbn");

        when(bookMap.toBookDTO(Mockito.<Book>any())).thenReturn(bookDTO);

        // Act
        List<BookDTO> actualAllBooks = bookService.getAllBooks();

        // Assert
        verify(bookRepositoryImplementation).findAll();
        verify(bookMap).toBookDTO(isA(Book.class));
        assertEquals(1, actualAllBooks.size());
        assertSame(bookDTO, actualAllBooks.get(0));
    }

    @Test
    void testGetAllBooks3() {
        // Arrange
        ArrayList<Book> bookList = new ArrayList<>();
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        bookList.add(buildResult);
        when(bookRepositoryImplementation.findAll()).thenReturn(bookList);
        when(bookMap.toBookDTO(Mockito.<Book>any())).thenThrow(new BookNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getAllBooks());
        verify(bookRepositoryImplementation).findAll();
        verify(bookMap).toBookDTO(isA(Book.class));
    }

    @Test
    void testGetBookById() {
        // Arrange
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        Optional<Book> ofResult = Optional.of(buildResult);
        when(bookRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        BookDTO bookDTO = new BookDTO("Dr", "JaneDoe", 1, "Isbn");

        when(bookMap.toBookDTO(Mockito.<Book>any())).thenReturn(bookDTO);

        // Act
        BookDTO actualBookById = bookService.getBookById(1);

        // Assert
        verify(bookRepositoryImplementation).findById(eq(1));
        verify(bookMap).toBookDTO(isA(Book.class));
        assertSame(bookDTO, actualBookById);
    }

    @Test
    void testGetBookById2() {
        // Arrange
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        Optional<Book> ofResult = Optional.of(buildResult);
        when(bookRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(bookMap.toBookDTO(Mockito.<Book>any())).thenThrow(new BookNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
        verify(bookRepositoryImplementation).findById(eq(1));
        verify(bookMap).toBookDTO(isA(Book.class));
    }

    @Test
    void testGetBookById3() {
        // Arrange
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
        verify(bookRepositoryImplementation).findById(eq(1));
    }

    @Test
    void testInsertBook() {
        // Arrange
        when(bookRepositoryImplementation.insert(Mockito.<Book>any())).thenReturn(1);
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        when(bookMap.toBook(Mockito.<BookDTO>any())).thenReturn(buildResult);
        BookDTO book = new BookDTO("Dr", "JaneDoe", 1, "Isbn");

        // Act
        bookService.insertBook(book);

        // Assert that nothing has changed
        verify(bookRepositoryImplementation).insert(isA(Book.class));
        verify(bookMap).toBook(isA(BookDTO.class));
        assertEquals("Dr", book.getTitle());
        assertEquals("Isbn", book.getIsbn());
        assertEquals("JaneDoe", book.getAuthor());
        assertEquals(1, book.getPublicationYear());
    }

    @Test
    void testDeleteBook() {
        // Arrange
        doNothing().when(bookRepositoryImplementation).deleteById(Mockito.<Integer>any());
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        Optional<Book> ofResult = Optional.of(buildResult);
        when(bookRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        bookService.deleteBook(1);

        // Assert that nothing has changed
        verify(bookRepositoryImplementation).deleteById(eq(1));
        verify(bookRepositoryImplementation).findById(eq(1));
    }

    @Test
    void testDeleteBook2() {
        // Arrange
        doThrow(new BookNotFoundException("An error occurred")).when(bookRepositoryImplementation)
                .deleteById(Mockito.<Integer>any());
        Book buildResult = Book.builder().author("JaneDoe").id(1).publicationYear(1).title("Dr").build();
        Optional<Book> ofResult = Optional.of(buildResult);
        when(bookRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1));
        verify(bookRepositoryImplementation).deleteById(eq(1));
        verify(bookRepositoryImplementation).findById(eq(1));
    }

    @Test
    void testDeleteBook3() {
        // Arrange
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepositoryImplementation.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1));
        verify(bookRepositoryImplementation).findById(eq(1));
    }
}
