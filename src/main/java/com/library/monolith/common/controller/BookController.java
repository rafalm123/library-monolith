package com.library.monolith.common.controller;

import com.library.monolith.common.model.dto.BookDetailsDTO;
import com.library.monolith.common.model.dto.BookDtoOverview;
import com.library.monolith.common.model.dto.BookService;
import com.library.monolith.common.model.dto.BookOverviewQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseEntity<List<BookDtoOverview>> getListOfBooks(@RequestBody BookOverviewQueryDto bookOverviewQueryDto){
            return new ResponseEntity<>(bookService.getBooksOverview(bookOverviewQueryDto),HttpStatus.OK);
    }

    //zwróć listę- jeśli będzie pusta- niech fron sobie to obsłuży
}

