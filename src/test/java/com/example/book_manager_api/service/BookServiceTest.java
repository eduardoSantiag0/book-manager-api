package com.example.book_manager_api.service;

import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Não use o banco de dados em memória, use o banco configurado
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Autowired
    private TestEntityManager em;

    //! Given, When, Then

    @Test
    @DisplayName("Deve checar se já existe um livro com o mesmo nome -> deve retornar True")
    void verificacaoSalvarNoRepositorio() {

        String tituloLivro = "Titulo";
        BookDTO dto = new BookDTO(tituloLivro, "Autor", 2023, null);

        //! Mocka o evento de salvar no repositório
        Book book = new Book(tituloLivro, "Autor", 2023);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        //! Act
        bookService.salvarRepositorio(dto);

        //! Mockar comportamento do repositório
        when(bookRepository.findBynomelivro(tituloLivro)).thenReturn(Optional.of(book));

        //! Assert
        Optional<Book> result = this.bookRepository.findBynomelivro(tituloLivro);
        assertTrue(result.isPresent(), "O livro não está presente no DB");

    }

}