package com.library.monolith.common.controller;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntity> getListOfBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable long id) {
        if (bookService.getById(id).isPresent()) {
            return new ResponseEntity<>(bookService.getById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<BookEntity> addBooksByStreams(@RequestBody BookEntity bookEntity) {
            BookEntity saveObj = bookService.save(bookEntity);
            if (bookService.listBooks().stream().anyMatch(bookEntity::equals)) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }

            //wcześniej porównywana była lista obiektów z jednym obiektem- to musiało zwrócić false
            //metodę equals używamy w ten sposób, że wywołujemy ją na jednym z porównywanych obiektów, a nie wywołujemy z Objects
            //TODO te komentarze są do wywalenia po przyswojeniu


            //TODO
            //wczytałem się i hoho- ta metoda jest do poprawienia- w ramach jednej operacji wykonujesz aż dwie operacje
            //najpierw save a potem pobierasz listę z bazy, i sprawdzasz czy jest tam zapisana ksiażka

            //tymczasem JPA działa tak, ze jeśli encja się zapisze, to bezie zwrócona z metody save
            //i wystarczy sprawdzić czy nie jest ona nullem
            //zatem do ifa wrzuć tylko saveObj != null i elo

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

