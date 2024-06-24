package com.example.library;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void testFindAllAuthors() {
        Author author1 = new Author(1, "Author1", new ArrayList<>());
        Author author2 = new Author(2, "Author2", new ArrayList<>());

        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.findAllAuthors();
        assertEquals(2, authors.size());
        assertEquals("Author1", authors.get(0).getName());
        assertEquals("Author2", authors.get(1).getName());
    }
}
