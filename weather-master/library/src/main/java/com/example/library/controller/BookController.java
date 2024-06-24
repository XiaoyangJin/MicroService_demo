package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book, @RequestParam List<String> authors) {
        return bookService.createBook(book, authors);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book, @RequestParam List<String> authors) {
        return bookService.updateBook(id, book, authors);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<Book> getBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTitle(title);
    }

    @GetMapping("/searchByAuthor")
    public List<Book> getBooksByAuthorName(@RequestParam String authorName) {
        return bookService.findBooksByAuthorName(authorName);
    }
}
