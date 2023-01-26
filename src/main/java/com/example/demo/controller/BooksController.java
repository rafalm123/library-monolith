package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getListOfBooks(){
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        if (bookService.getById(id).isPresent()) {
            return new ResponseEntity<>(bookService.getById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
