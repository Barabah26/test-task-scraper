package com.example.testtasktraineejave.controller;

import com.example.testtasktraineejave.scraper.BookScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookScraper bookScraper;

    @GetMapping("/scrape/{totalPages}")
    public String scrapeBooks(@PathVariable int totalPages) {
        try {
            bookScraper.scrapeBooksFromMultiplePages(totalPages);
            return "Books scraped and saved successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred while scraping books.";
        }
    }
}

