package com.example.university.service;

import com.example.university.entity.University;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface UniversityService {
    //1st api: search by name
    University[] getAllUniversities();
    //2nd api: accept list of countries and send request using multithreading
    List<University> getAllUniversitiesByCountries(List<String> countries);
}
