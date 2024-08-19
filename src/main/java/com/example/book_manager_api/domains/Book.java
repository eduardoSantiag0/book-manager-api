package com.example.book_manager_api.domains;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "livros")
@Entity(name = "Livro")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomelivro;
    private String autor;
    private Integer anopublicacao;
    private Boolean disponivel;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    //Construtor normal
    public Book (String nomelivro, String nomeAutor, int anopublicacao, Genero genero) {
        this.nomelivro = nomelivro;
        this.autor = nomeAutor;
        this.anopublicacao = anopublicacao;
        this.genero = genero;
        this.disponivel = true;
    }


    //Construtor sem genero
    public Book (String nomelivro, String nomeAutor, int anopublicacao) {
        this.nomelivro = nomelivro;
        this.autor = nomeAutor;
        this.anopublicacao = anopublicacao;
        this.disponivel = true;
    }

    //Construtor com DTO
    public Book(BookDTO bookDTO) {
        this.nomelivro = bookDTO.nomelivro();
        this.autor = bookDTO.autor();
        this.anopublicacao = bookDTO.anopublicacao();
        this.genero = bookDTO.genero();
        this.disponivel = true;
    }

    public void atualizarCadastro (BookAtualizacaoDados dados) {
        if (dados.nomelivro() != null) {
            this.nomelivro = dados.nomelivro();
        }

        if (dados.autor() != null) {
            this.autor = dados.autor();
        }

        if (dados.anopublicacao() != null) {
            this.anopublicacao = dados.anopublicacao();
        }

        if (dados.genero() != null) {
            this.genero = dados.genero();
        }
    }

//    public void emprestar() {
//    }

}
