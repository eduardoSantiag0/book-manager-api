package com.example.book_manager_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
@ControllerAdvice
//! Cada método trata uma exceção específica
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    private ResponseEntity<RestErrorMessage> bookNotFoundHandlers (BookNotFoundException exception) {
        RestErrorMessage responseTratada = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseTratada);
    }

    @ExceptionHandler(BookSingleRegistryException.class)
    private ResponseEntity<RestErrorMessage> bookAlreadyRegistred (BookSingleRegistryException exception) {
        RestErrorMessage resp = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(BookPublicationYearInvalidException.class)
    private ResponseEntity<RestErrorMessage> bookPublicationYearInvalid (BookPublicationYearInvalidException exception) {
        RestErrorMessage resp = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

}
