package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getListOfBooks() {
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
    @PostMapping
    public ResponseEntity<Book> addBooksByStreams(@RequestBody Book book) {
            Book saveObj = bookService.save(book);
            if (Objects.equals(bookService.listBooks(), book)) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

