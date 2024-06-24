package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AuthorBook> authorBook = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorBook> getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(List<AuthorBook> authorBook) {
        this.authorBook = authorBook;
    }

    public Book() {
    }

    public Book(int id, String title, List<AuthorBook> authorBook) {
        this.id = id;
        this.title = title;
        this.authorBook = authorBook;
    }
}
