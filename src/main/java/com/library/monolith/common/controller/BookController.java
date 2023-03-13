package com.library.monolith.common.controller;

import com.library.monolith.common.service.BookDetailsDTO;
import com.library.monolith.common.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDetailsDTO> getBookByUUID(@PathVariable UUID uuid) {

        try {
            BookDetailsDTO bookDTOByReleaseUuid = bookService.getBookDTOByReleaseUuid(uuid);
            return new ResponseEntity<>(bookDTOByReleaseUuid, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDetailsDTO>> getListOfBooks(){

        try {
            List<BookDetailsDTO> listOfBooks = bookService.getListOfBooks();
            return new ResponseEntity<>(listOfBooks,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

