package com.library.monolith.common.controller;

import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import com.library.monolith.common.repository.BookCopyRepository;
import com.library.monolith.common.service.BookCopyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/books/{bookUuid}/copies")
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookCopyRepository bookCopyRepository;

    public BookCopyController(
                              BookCopyService bookCopyService,
                              BookCopyRepository bookCopyRepository) {
        this.bookCopyService = bookCopyService;
        this.bookCopyRepository = bookCopyRepository;
    }


    @GetMapping
    public List<ReleaseCopyEntity> getListOfCopies(@PathVariable UUID bookUuid) {
        return bookCopyService.listCopies();
    }

    @GetMapping("/{copyUuid}")
    public ResponseEntity<ReleaseCopyEntity> getCopyByUuid(@PathVariable UUID copyUuid) {
        if (bookCopyService.getCopyByUuid(copyUuid).isPresent()) {
            return new ResponseEntity<>(bookCopyService.getCopyByUuid(copyUuid).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<ReleaseCopyEntity> addCopy(@RequestBody ReleaseCopyEntity releaseCopyEntity, @PathVariable UUID bookUuid) {
        Optional<ReleaseCopyEntity> existCopy = bookCopyRepository
                .findBookCopyByBookCopyUuid(releaseCopyEntity.getBookCopyUuid());

        if (existCopy.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        ReleaseCopyEntity saveObj = bookCopyService.saveCopy(releaseCopyEntity);
        if (saveObj!= null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ReleaseCopyEntity> modifyCopy(@PathVariable UUID uuid, @RequestBody ReleaseCopyEntity updatedCopy){
        Optional<ReleaseCopyEntity> copy = bookCopyService.getCopyByUuid(uuid);
        if (copy.isPresent()){
            bookCopyService.removeCopy(getCopyByUuid(uuid).getBody());
            bookCopyService.saveCopy(updatedCopy);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ReleaseCopyEntity> removeBook(@PathVariable UUID uuid){
        Optional<ReleaseCopyEntity> copy = bookCopyService.getCopyByUuid(uuid);
        if (copy.isPresent()){
            bookCopyService.removeCopy(getCopyByUuid(uuid).getBody());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
