package com.example.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "author_book")
public class AuthorBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_name", referencedColumnName = "name")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_title", referencedColumnName = "title")
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public AuthorBook() {
    }

    public AuthorBook(int id, Author author, Book book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }
}
