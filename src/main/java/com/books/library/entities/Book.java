package com.books.library.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
}