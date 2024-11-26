package com.example.testtasktraineejave.repository;

import com.example.testtasktraineejave.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
