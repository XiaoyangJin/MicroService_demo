package com.example.library.service.impl;

import com.example.library.entity.Author;
import com.example.library.exceptions.EntityNotFoundException;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author updateAuthor(int id, Author updatedAuthor) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(updatedAuthor.getName());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + id));
    }

    @Override
    @Transactional
    public void deleteAuthor(int id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Author not found with id " + id);
        }
    }

    @Override
    public List<Author> findAuthorsByName(String name) {
        return authorRepository.findByName(name);
    }

}
