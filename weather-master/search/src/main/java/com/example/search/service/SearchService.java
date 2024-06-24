package com.example.search.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<String> getBooks();
    List<String> getAuthors();
    List<String> getUniversityDetails();
    String fallbackBooks();
    String fallbackAuthors();
    String fallbackUniversityDetails();
}
