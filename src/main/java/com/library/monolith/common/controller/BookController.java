package com.library.monolith.common.controller;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.repository.BookRepository;
import com.library.monolith.common.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDto> getBookByUUID(@PathVariable UUID uuid) {
        if (bookService.getBookByUuid(uuid).isPresent()) {
            return new ResponseEntity<>(bookService.getBookByUuid(uuid).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

