package com.example.book_manager_api.exceptions;

public class BookSingleRegistryException extends RuntimeException {

    public BookSingleRegistryException () {
        super();
    }

    public BookSingleRegistryException (String m) {
        super(m);
    }
}
