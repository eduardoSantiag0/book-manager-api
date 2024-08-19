package com.example.book_manager_api.service;

import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Apenas um Cadastro de Livro
// Método para salvar no repositório
//todo Ano de Publicação deve ser menor ou igual que o ano atual
//todo Verificação de cadastro ou atualização de livros
//todo Paginação e Ordenação nas requisições GET

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

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


}
