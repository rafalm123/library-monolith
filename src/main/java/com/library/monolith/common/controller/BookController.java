package com.library.monolith.common.controller;

import com.library.monolith.common.model.dto.BookDetailsDTO;
import com.library.monolith.common.model.dto.BookOverviewDTO;
import com.library.monolith.common.model.dto.BookOverviewQueryDto;
import com.library.monolith.common.model.dto.BookServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImplementation bookServiceImplementation;

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookServiceImplementation.getBookDetailsDto(id));
    }

//    @GetMapping("")
//    public ResponseEntity<List<BookOverviewDTO>> getAllOverviews(){
//        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewDtoList());
//    }

    @GetMapping("/list")
    public ResponseEntity<Page<BookOverviewDTO>> getOverviewPage(@RequestBody BookOverviewQueryDto queryDto){
        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewPage(queryDto));
    }
}
