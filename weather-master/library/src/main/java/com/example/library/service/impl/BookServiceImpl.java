package com.example.library.service.impl;

import com.example.library.entity.Author;
import com.example.library.entity.AuthorBook;
import com.example.library.entity.Book;
import com.example.library.exceptions.EntityNotFoundException;
import com.example.library.repository.AuthorBookRepository;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @PersistenceContext
    private EntityManager entityManager;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBookRepository authorBookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorBookRepository authorBookRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorBookRepository = authorBookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Book createBook(Book book, List<String> authors) {
        bookRepository.save(book);

        for (String authorName : authors) {
            Author author = findOrCreateAuthor(authorName);
            AuthorBook authorBook = new AuthorBook();
            authorBook.setAuthor(author);
            authorBook.setBook(book);
            authorBookRepository.save(authorBook);
        }

        return book;
    }

    @Override
    @Transactional
    public Book updateBook(int id, Book updatedBook, List<String> authors) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    authorBookRepository.deleteAllByBook(existingBook);

                    for (String authorName : authors) {
                        Author author = findOrCreateAuthor(authorName);
                        AuthorBook authorBook = new AuthorBook();
                        authorBook.setAuthor(author);
                        authorBook.setBook(existingBook);
                        authorBookRepository.save(authorBook);
                    }

                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Override
    @Transactional
    public void deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            authorBookRepository.deleteAllByBookId(id);
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        return authorBookRepository.findByAuthorName(authorName)
                .stream()
                .map(AuthorBook::getBook)
                .distinct()
                .collect(Collectors.toList());
    }

    private Author findOrCreateAuthor(String authorName) {
        return authorRepository.findByName(authorName)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Author author = new Author();
                    author.setName(authorName);
                    return authorRepository.save(author);
                });
    }
}
