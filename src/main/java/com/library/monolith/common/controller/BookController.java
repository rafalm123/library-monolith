package com.library.monolith.common.controller;

import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.dto.book.BookOverviewQueryDto;
import com.library.monolith.common.model.dto.book.BookServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/library/")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImplementation bookServiceImplementation;

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookServiceImplementation.getBookDetailsDto(id));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookOverviewDTO>> getAllOverviews(){
        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewDtoList());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<BookOverviewDTO>> getOverviewPage(@RequestBody BookOverviewQueryDto queryDto){
        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewPage(queryDto));
    }
}
