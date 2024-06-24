package com.example.library.repository;

import com.example.library.entity.AuthorBook;
import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
    @Query("SELECT ab FROM AuthorBook ab WHERE ab.author.name = :authorName")
    List<AuthorBook> findByAuthorName(String authorName);

    @Query("SELECT ab FROM AuthorBook ab WHERE ab.book.title = :bookTitle")
    List<AuthorBook> findByBookTitle(String bookTitle);

    void deleteAllByBook(Book book);
    void deleteAllByBookId(int bookId);
}
