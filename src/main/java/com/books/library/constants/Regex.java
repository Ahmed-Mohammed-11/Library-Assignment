package com.books.library.constants;

public record Regex() {
    public static final String TITLE_REGEX = "^[a-zA-Z0-9]*$";
    public static final String AUTHOR_REGEX = "^[a-zA-Z]*$";
    public static final String PUBLICATION_YEAR_REGEX = "^[0-9]+$";
    public static final String PATRON_NAME_REGEX = "^[a-zA-Z]*$";
}
