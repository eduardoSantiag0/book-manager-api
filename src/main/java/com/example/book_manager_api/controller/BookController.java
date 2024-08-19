package com.example.book_manager_api.controller;

import com.example.book_manager_api.domains.Book;
import com.example.book_manager_api.domains.BookAtualizacaoDados;
import com.example.book_manager_api.domains.BookDTO;
import com.example.book_manager_api.domains.BookRepository;
import com.example.book_manager_api.exceptions.BookNotFoundException;
import com.example.book_manager_api.exceptions.BookPublicationYearInvalidException;
import com.example.book_manager_api.exceptions.BookSingleRegistryException;
import com.example.book_manager_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @PostMapping
    @Transactional
    //* UriComponentsBuilder => devolve a URI do recurso no cabeçalho Location da resposta.
    public ResponseEntity cadastrarLivro (@RequestBody @Validated BookDTO bookDTO, UriComponentsBuilder uriBuilder) {

        if (bookService.verificacaoCadastroUnico(bookDTO)) {
            throw new BookSingleRegistryException("Livro já está cadastrado!");
        }

        if (bookService.verificacaoAnoPublicacao(bookDTO.anopublicacao())) {
            throw  new BookPublicationYearInvalidException("Ano de publicação está inválido");
        }

        var book = bookService.salvarRepositorio(bookDTO);

        var uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body("Livro Registrado com Sucesso!");
    }

    //? ResponseEntity represents the whole HTTP response: status code, headers, and body.
    @GetMapping
    public ResponseEntity <List<BookDTO>> verLivros () {

        List<BookDTO> livros = bookRepository.findAll().stream()
                .map(b -> new BookDTO(b.getNomelivro(), b.getAutor(), b.getAnopublicacao(), b.getGenero()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(livros);
    }

    @GetMapping ("/{nomeLivro}")
    public ResponseEntity<BookDTO> buscarLivroEspecifico (@PathVariable String nomeLivro) {
        Optional<Book> livroProcurado = Optional.ofNullable(bookRepository.findBynomelivro(nomeLivro)
                .orElseThrow(() -> new BookNotFoundException("Livro com nome '" + nomeLivro + "' não encontrado")));
        if (livroProcurado.isPresent()) {
            var livroAchado = livroProcurado.get();
            BookDTO dto = new BookDTO(livroAchado.getNomelivro(), livroAchado.getAutor(), livroAchado.getAnopublicacao(), livroAchado.getGenero());
            return ResponseEntity.ok(dto);
        }
        else { return ResponseEntity.notFound().build(); }
    }


    @PutMapping
    @Transactional
    public void atualizarLivro (@RequestBody BookAtualizacaoDados dados) {
        var book = bookRepository.findById(dados.id());
        if (book.isPresent()) {
            book.get().atualizarCadastro(dados);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarLivro (@PathVariable Long id) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}






