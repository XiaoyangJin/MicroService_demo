package com.example.university.controller;

import com.example.university.entity.University;
import com.example.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/university")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUniversities(){
        return new ResponseEntity<>(universityService.getAllUniversities(), HttpStatus.OK);
    }

    @GetMapping(params = "countries")
    public ResponseEntity<?> getAllUniversitiesByCountries(@RequestParam List<String> countries){
        return new ResponseEntity<>(universityService.getAllUniversitiesByCountries(countries), HttpStatus.OK);
    }
}
