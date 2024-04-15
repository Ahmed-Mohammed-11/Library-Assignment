package com.books.library.constants;

public record ResponseMessageConstants() {
    public static final String BOOK_ADDED_SUCCESSFULLY = "Book added successfully";
    public static final String BOOK_UPDATED_SUCCESSFULLY = "Book updated successfully";
    public static final String BOOK_DELETED_SUCCESSFULLY = "Book deleted successfully";
    public static final String BOOK_NOT_FOUND = "Book not found";
    public static final String PATRON_ADDED_SUCCESSFULLY = "Patron added successfully";
    public static final String PATRON_UPDATED_SUCCESSFULLY = "Patron updated successfully";
    public static final String PATRON_DELETED_SUCCESSFULLY = "Patron deleted successfully";
    public static final String PATRON_NOT_FOUND = "Patron not found";
    public static final String BOOK_TITLE_SIZE = "Title size should be from 1 to 255 characters";
    public static final String BOOK_TITLE_FORMAT = "title should only contain letters and numbers";
    public static final String BOOK_AUTHOR_SIZE = "Author size should be from 1 to 255 characters";
    public static final String BOOK_AUTHOR_FORMAT = "Author should only contain letters";
    public static final String BOOK_PUBLICATION_YEAR_FORMAT = "Publication year should only contain numbers";
    public static final String BOOK_ISBN_SIZE = "ISBN size should be from 1 to 255 characters";
    public static final String PATRON_NAME_FORMAT = "Name should only contain letters";
    public static final String PATRON_NAME_SIZE = "Name size should be from 1 to 255 characters";
    public static final String PATRON_CONTACT_INFORMATION_SIZE = "Contact information size should be from 1 to 255 characters";
    public static final String BORROWED_BOOKS = "Book Borrowed Successfully";
    public static final String RETURNED_BOOKS = "Book Returned Successfully";
    public static final String BOOK_IS_BORROWED = "Book is already borrowed";
    public static final String BOOK_IS_NOT_BORROWED = "Book is not borrowed";
}
