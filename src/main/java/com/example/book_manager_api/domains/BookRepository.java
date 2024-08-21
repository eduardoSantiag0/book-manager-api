package com.example.book_manager_api.domains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface  BookRepository extends JpaRepository <Book,Long> {
    Optional<Book> findById(Long id);

    Optional<Book> findBynomelivro(String nome);

    @Query(value = "SELECT * FROM livros ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Book> findRandomBook();
}
