package com.example.book_manager_api.exceptions;

public class AuthorNameInvalidException extends RuntimeException{
    public AuthorNameInvalidException () {
        super ();
    }

    public AuthorNameInvalidException (String m) {
        super(m);
    }


}
