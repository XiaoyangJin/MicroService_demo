package com.example.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AuthorBook> authorBook = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorBook> getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(List<AuthorBook> authorBook) {
        this.authorBook = authorBook;
    }

    public Author(int id, String name, List<AuthorBook> authorBook) {
        this.id = id;
        this.name = name;
        this.authorBook = authorBook;
    }

    public Author() {
    }
}
