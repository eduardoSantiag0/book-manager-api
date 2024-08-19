package com.example.book_manager_api.domains;

import lombok.NonNull;

public record BookAtualizacaoDados(

        Long id,

        String nomelivro,

        String autor,

        Integer anopublicacao,

        Genero genero
) {
}
