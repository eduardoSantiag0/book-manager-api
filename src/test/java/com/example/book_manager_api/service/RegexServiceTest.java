package com.example.book_manager_api.service;

import com.example.book_manager_api.domains.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RegexServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    @DisplayName("Se o nome só tem digitos, lançar erro")
    void verificarNomeAutorCenario01() {
        String nomeComDigitos = "202408";
        boolean result = bookService.verificarNomeAutor(nomeComDigitos);
        assertTrue(result, "Não foi possível detectar os números na String");
    }

    @Test
    @DisplayName("Se o nome tem letras e digitos, lançar erro")
    void verificarNomeAutorCenario02() {
        String nomeComDigitos = "ERE202408";
        boolean result = bookService.verificarNomeAutor(nomeComDigitos);
        assertTrue(result, "Não foi possível detectar os números na String");
    }


    @Test
    @DisplayName("Se o nome tem apenas letras, retorna falso")
    void verificarNomeAutorCenario03() {
        String nomeSemDigitos = "EOIASFER";
        boolean result = bookService.verificarNomeAutor(nomeSemDigitos);
        assertFalse(result, "Retornou true");
    }

    @Test
    @DisplayName("Se o nome tem caractéres especiais, lançar erro")
    void verificarNomeAutorCenario04() {
        String nomeComEspecial = "EREDE&&";
        boolean result = bookService.verificarNomeAutor(nomeComEspecial);
        assertTrue(result, "Não foi possível detectar os números na String");
    }

    @Test
    @DisplayName("Se o nome só tem chars, não deve lançar erro")
    void verificarNomeAutorCenario05() {
        String nomeComEspecial = "Shakespeare";
        boolean result = bookService.verificarNomeAutor(nomeComEspecial);
        assertFalse(result, "Um erro foi lançado");
    }
    @Test
    @DisplayName("Se o nome tiver espaço, não deve lançar erro")
    void verificarNomeAutorCenario06() {
        String nomeComEspecial = "William Shakespeare";
        boolean result = bookService.verificarNomeAutor(nomeComEspecial);
        assertFalse(result, "Um erro foi lançado");
    }

}
