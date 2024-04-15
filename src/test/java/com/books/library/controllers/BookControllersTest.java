package com.books.library.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.books.library.dtos.BookDTO;
import com.books.library.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookControllers.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookControllersTest {
    @Autowired
    private BookControllers bookControllers;

    @MockBean
    private BookService bookService;

    @Test
    void testGetAllBooks() throws Exception {
        // Arrange
        when(bookService.getAllBooks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bookControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
    @Test
    void testGetBookById() throws Exception {
        // Arrange
        when(bookService.getBookById(Mockito.<Integer>any())).thenReturn(new BookDTO("Dr", "JaneDoe", 1, "Isbn"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bookControllers)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"title\":\"Dr\",\"author\":\"JaneDoe\",\"publicationYear\":1,\"isbn\":\"Isbn\"}"));
    }

    @Test
    void testDeleteBook() throws Exception {
        // Arrange
        doNothing().when(bookService).deleteBook(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/books/{id}", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bookControllers)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Book deleted successfully"));
    }

    @Test
    void testDeleteBook2() throws Exception {
        // Arrange
        doNothing().when(bookService).deleteBook(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/books/{id}", 1);
        requestBuilder.contentType("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(bookControllers)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Book deleted successfully"));
    }
}
