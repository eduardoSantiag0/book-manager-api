package com.example.book_manager_api.domains;

import lombok.NonNull;
import javax.validation.constraints.Pattern;


public record BookDTO(

        @NonNull
        String nomelivro,

//        @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "O nome do autor  deve ser uma string")
//        @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s]+$", message = "nomelivro must be a valid string and cannot contain special characters.")
        @NonNull
        String autor,

        Integer anopublicacao,

        Genero genero
) {
}
