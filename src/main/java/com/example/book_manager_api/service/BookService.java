package com.example.book_manager_api.service;

import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

// Apenas um Cadastro de Livro
// Método para salvar no repositório
// Ano de Publicação deve ser menor ou igual que o ano atual
//todo Paginação e Ordenação nas requisições GET
//todo Verificação de cadastro ou atualização de livros

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Page<BookDTO> listarTodos (Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(b -> new BookDTO(b.getNomelivro(),b.getAutor(), b.getAnopublicacao(), b.getGenero() ));
    }

    public boolean verificacaoCadastroUnico (BookDTO bookDTO) {
        var livroEncontrado = bookRepository.findBynomelivro(bookDTO.nomelivro());

        if (livroEncontrado.isPresent()) {
            return true;
        }

        return false;
    }

    public Book salvarRepositorio (BookDTO dados) {
        var book = new Book(dados);
        bookRepository.save(book);

        return book;
    }

    public boolean verificacaoAnoPublicacao (int dado) {
        int anoAtual = LocalDateTime.now().getYear();
        if (dado > anoAtual) {
            return true;
        }

        return false;
    }

    public Optional<BookDTO> getRandomBook () {
        var book = bookRepository.findRandomBook();
        // Transformar Entity em DTO
        return book.map(b -> new BookDTO(b.getNomelivro(), b.getAutor(), b.getAnopublicacao(), b.getGenero()));

    }


}
