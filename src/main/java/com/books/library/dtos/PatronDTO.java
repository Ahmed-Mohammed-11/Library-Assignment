package com.books.library.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.books.library.constants.Regex.PATRON_NAME_REGEX;
import static com.books.library.constants.ResponseMessageConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PatronDTO {
    @Size(min = 1, max = 255, message = PATRON_NAME_SIZE)
    @Pattern(regexp = PATRON_NAME_REGEX, message = PATRON_NAME_FORMAT)
    private String name;

    @Size(min = 1, max = 255, message = PATRON_CONTACT_INFORMATION_SIZE)
    private String contactInformation;
}