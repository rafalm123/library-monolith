package com.library.monolith.common.controller;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.repository.BookRepository;
import com.library.monolith.common.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<BookEntity> getListOfBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<BookEntity> getBookByUUID(@PathVariable UUID uuid) {
        if (bookService.getBookByUuid(uuid).isPresent()) {
            return new ResponseEntity<>(bookService.getBookByUuid(uuid).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity bookEntity) {
        Optional<BookEntity> existBook = bookRepository.findBookByBookUuid(bookEntity.getBookUuid());

        if (existBook.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        BookEntity saveObj = bookService.saveBook(bookEntity);
            if (saveObj!= null) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<BookEntity> modifyBook(@PathVariable UUID uuid,@RequestBody BookEntity updatedBook){
        Optional<BookEntity> book = bookService.getBookByUuid(uuid);
        if (book.isPresent()){
            bookService.removeBook(getBookByUUID(uuid).getBody());
            bookService.saveBook(updatedBook);
            return new ResponseEntity<>(HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<BookEntity> removeBook(@PathVariable UUID uuid){
        Optional<BookEntity> book = bookService.getBookByUuid(uuid);
        if (book.isPresent()){
            bookService.removeBook(getBookByUUID(uuid).getBody());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

