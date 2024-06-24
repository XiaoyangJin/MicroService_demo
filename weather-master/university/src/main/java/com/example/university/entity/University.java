package com.example.university.entity;

import java.util.List;


public class University {
    private String name;
    private List<String> domains;
    private List<String> web_pages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(List<String> web_pages) {
        this.web_pages = web_pages;
    }

    public University() {
    }

    public University(String name, List<String> domains, List<String> web_pages) {
        this.name = name;
        this.domains = domains;
        this.web_pages = web_pages;
    }
}
