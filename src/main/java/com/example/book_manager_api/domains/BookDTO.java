package com.example.book_manager_api.domains;

import lombok.NonNull;

public record BookDTO(

        @NonNull
        String nomelivro,

        @NonNull
        String autor,

        Integer anopublicacao,

        Genero genero
) {
}
