package com.library.monolith.common.controller;

import com.library.monolith.common.service.BookDetailsDTO;
import com.library.monolith.common.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDetailsDTO> getBookByUUID(@PathVariable UUID uuid) {
        return bookService.getBookByUuid(uuid).map
    }
}

