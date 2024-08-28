package com.example.book_manager_api.controller;


import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import com.example.book_manager_api.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Mock
    private Book book;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnStatus200AndRegisterBook() throws Exception {
        BookDTO mockBookDTO = new BookDTO("Titulo", "Autor", 2023, null);

        when(bookService.salvarRepositorio(mockBookDTO)).thenReturn(new Book(mockBookDTO));

        String bookDTOJson = new ObjectMapper().writeValueAsString(mockBookDTO);

        MockHttpServletResponse response = mockMvc.perform(
                post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookDTOJson)
        ).andReturn().getResponse();

        assertEquals(201, response.getStatus());

    }

    @Test
    void shouldReturnStatus200AndRandomBook() throws Exception {

//        Book mockBook = new Book("Titulo", "Autor", 2023, null);
        BookDTO mockBookDTO = new BookDTO("Titulo", "Autor", 2023, null);

//        when(bookRepository.findRandomBook()).thenReturn(Optional.of(mockBook));
        when(bookService.getRandomBook()).thenReturn(Optional.of(mockBookDTO));

        MockHttpServletResponse response = mockMvc.perform(
                get("/books/random")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("Titulo"));
        assertTrue(response.getContentAsString().contains("Autor"));

    }

    @Test
    void shouldReturnBookByName() throws Exception {

        String nome = "Crime e Castigo";
        Book dado = new Book(nome, "Fyodor", 2023, null);

        when(bookRepository.findBynomelivro(nome)).thenReturn(Optional.of(dado));

        var response = mockMvc.perform(
                get("/books/"+ dado.getNomelivro())
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());

    }

}
