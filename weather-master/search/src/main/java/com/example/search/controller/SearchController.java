package com.example.search.controller;

import com.example.search.config.GeneralResponse;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        List<String> books = searchService.getBooks();
        List<String> authors = searchService.getAuthors();
        List<String> details = searchService.getUniversityDetails();

        String result = String.format("Books: %s, Authors: %s, Details: %s", books, authors, details);
        return new ResponseEntity<>(new GeneralResponse("200", "Timestamp", result), HttpStatus.OK);
    }

//    @GetMapping("/weather/search")
//    public ResponseEntity<?> getDetails() {
//        //TODO
//        return new ResponseEntity<>("this is search service", HttpStatus.OK);
//    }
}
