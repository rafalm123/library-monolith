package com.example.demo.service;

import com.example.demo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceI {

    Iterable<Book> listBooks();

    Book save(Book book);

    Iterable<Book> saveList(List<Book> listOfBooks);

    Optional<Book> getById(long id);
}
