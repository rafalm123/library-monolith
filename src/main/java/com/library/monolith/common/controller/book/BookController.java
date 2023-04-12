package com.library.monolith.common.controller.book;

import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.dto.book.BookOverviewQueryDto;
import com.library.monolith.common.model.dto.book.BookServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImplementation bookServiceImplementation;


    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> getBookDetailsDtoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookServiceImplementation.getBookDetailsDto(id));
    }

    @GetMapping()
    public ResponseEntity<List<BookOverviewDTO>> getAllBookOverviewDtos(){
        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewDtoList());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<BookOverviewDTO>> getBookOverviewDtosPage(@RequestBody BookOverviewQueryDto queryDto){
        return ResponseEntity.ok(bookServiceImplementation.getBookOverviewPage(queryDto));
    }


}
