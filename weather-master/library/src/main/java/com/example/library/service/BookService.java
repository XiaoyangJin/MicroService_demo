package com.example.library.service;

import com.example.library.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<Book> findAllBooks();
    Optional<Book> findBookById(int id);
    Book createBook(Book book, List<String> authors);
    Book updateBook(int id, Book book, List<String> authors);
    void deleteBook(int id);
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthorName(String authorName);
}
