package com.library.monolith.common.controller;

import com.library.monolith.common.service.BookDetailsDTO;
import com.library.monolith.common.service.BookDtoOverview;
import com.library.monolith.common.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{uuid}")
    public ResponseEntity<BookDetailsDTO> getBookByUUID(@PathVariable UUID uuid) {

        try {
            return new ResponseEntity<>(bookService.getBookDetailsDto(uuid), HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDtoOverview>> getListOfBooks(){

        try {
            List<BookDtoOverview> listOfBooks = bookService.getBooksOverview();
            return new ResponseEntity<>(listOfBooks,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

