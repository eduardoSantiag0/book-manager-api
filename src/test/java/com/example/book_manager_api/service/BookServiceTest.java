package com.example.book_manager_api.service;

import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

        bookService.salvarRepositorio(dto);

        Optional<Book> result = this.bookRepository.findBynomelivro(tituloLivro);

        assertTrue(result.isPresent(), "O livro não está presente no DB");

    }


    @Test
    @DisplayName("Deve checar se já existe um livro com o mesmo nome -> deve retornar True")
    void verificacaoCadastroUnicoCenario1() {

        String tituloLivro = "Titulo";

        BookDTO dto = new BookDTO(tituloLivro, "Autor", 2023, null);
        bookService.salvarRepositorio(dto);

        BookDTO dto2 = new BookDTO(tituloLivro, "Autor", 2023, null);
        bookService.salvarRepositorio(dto2);

        boolean result = bookService.verificacaoCadastroUnico(dto2);

        assertTrue(result, "O livro está presente no DB");
    }



    @Test
    void getRandomBook() {

        List<BookDTO> livros = Arrays.asList(
                new Book("Título 1", "Autor 1", 2020, null),
                new Book("Título 2", "Autor 2", 2021, null),
                new Book("Título 3", "Autor 3", 2022, null),
                new Book("Título 4", "Autor 4", 2023, null),
                new Book("Título 5", "Autor 5", 2024, null)
        ).stream()
                .map(b -> new BookDTO (b.getNomelivro(), b.getAutor(), b.getAnopublicacao(), b.getGenero()))
                .collect(Collectors.toList());

        livros.forEach(b -> bookService.salvarRepositorio(b));


        Optional<BookDTO> book1 = bookService.getRandomBook();
        Optional<BookDTO> book2 = bookService.getRandomBook();
        Optional<BookDTO> book3 = bookService.getRandomBook();

        assertTrue(book1.isPresent(), "book1 não deveria ser vazio");
        assertTrue(book2.isPresent(), "book2 não deveria ser vazio");
        assertTrue(book3.isPresent(), "book3 não deveria ser vazio");

        assertNotEquals(book1.get().nomelivro(), book2.get().nomelivro(), "book1 e book2 não deveriam ser iguais");
        assertNotEquals(book1.get().nomelivro(), book3.get().nomelivro(), "book1 e book3 não deveriam ser iguais");
        assertNotEquals(book2.get().nomelivro(), book3.get().nomelivro(), "book2 e book3 não deveriam ser iguais");
    }

}