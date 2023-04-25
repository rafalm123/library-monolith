package com.library.monolith.common.controller.book;

import com.library.monolith.common.model.dto.book.*;
import com.library.monolith.common.service.book.BookServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImplementation bookServiceImplementation;


    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> getBookDetailsDtoByReleaseId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookServiceImplementation.getBookDetailsDto(id));
    }

    @GetMapping()
    public ResponseEntity<List<BookOverviewDTO>> getAllBookOverviewDtos(){
        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewDtoList());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookCreateDTO bookCreateDTO){
        return ResponseEntity.ok(bookServiceImplementation.addBook(bookCreateDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody BookDeleteDTO bookDeleteDTO){
        return ResponseEntity.ok(bookServiceImplementation.deleteBook(bookDeleteDTO));
    }

}
