package com.example.library.service;

import com.example.library.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<Author> findAllAuthors();
    Optional<Author> findAuthorById(int id);
    Author createAuthor(Author author);
    Author updateAuthor(int id, Author author);
    void deleteAuthor(int id);
    List<Author> findAuthorsByName(String name);
}
