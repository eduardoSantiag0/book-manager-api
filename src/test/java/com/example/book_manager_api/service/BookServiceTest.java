package com.example.book_manager_api.service;

import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void verificacaoCadastroUnico() {

        BookDTO dto = new BookDTO("Titulo", "Autor", 2023, null);

        Book existingBook = new Book();
        when(bookRepository.findBynomelivro("Titulo")).thenReturn(Optional.of(existingBook));

        boolean resultado = bookService.verificacaoCadastroUnico(dto);

        assertTrue(resultado, "O livro já está cadastrado, o método deveria retornar true.");


    }

    @Test
    void salvarRepositorio() {
    }

    @Test
    void verificacaoAnoPublicacao() {
    }

    @Test
    void getRandomBook() {
    }

    @Test
    void verificarNomeAutor() {
    }
}