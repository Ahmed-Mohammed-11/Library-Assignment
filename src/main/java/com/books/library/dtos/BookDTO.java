package com.books.library.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.NumberFormat;

import static com.books.library.constants.Regex.*;
import static com.books.library.constants.ResponseMessageConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookDTO {

    @Size(min = 1, max = 255, message = BOOK_TITLE_SIZE)
    @Pattern(regexp = TITLE_REGEX, message = BOOK_TITLE_FORMAT)
    private String title;

    @Size(min = 1, max = 255, message = BOOK_AUTHOR_SIZE)
    @Pattern(regexp = AUTHOR_REGEX, message = BOOK_AUTHOR_FORMAT)
    private String author;

    @Digits(integer = 4, fraction = 0, message = BOOK_PUBLICATION_YEAR_FORMAT)
    private int publicationYear;

    @Size(min = 1, max = 255, message = BOOK_ISBN_SIZE)
    private String isbn;
}