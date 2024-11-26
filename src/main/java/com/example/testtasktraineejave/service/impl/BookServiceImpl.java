package com.example.testtasktraineejave.service.impl;

import com.example.testtasktraineejave.entity.Book;
import com.example.testtasktraineejave.repository.BookRepository;
import com.example.testtasktraineejave.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }
}
