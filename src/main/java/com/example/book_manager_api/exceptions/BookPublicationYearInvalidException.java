package com.example.book_manager_api.exceptions;

public class BookPublicationYearInvalidException extends RuntimeException {

    public BookPublicationYearInvalidException () {
        super();
    }

    public BookPublicationYearInvalidException(String m) {
        super(m);

    }
}
