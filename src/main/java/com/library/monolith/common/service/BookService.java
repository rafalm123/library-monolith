package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.repository.BookRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookReleaseRepository bookReleaseRepository;
    private final BookDtoMapper bookDtoMapper;

    public BookService(BookRepository bookRepository, BookReleaseRepository bookReleaseRepository) {
        this.bookRepository = bookRepository;
        this.bookReleaseRepository = bookReleaseRepository;
        this.bookDtoMapper = new BookDtoMapper();
    }

    public BookDetailsDTO getBookDetailsDto(UUID uuid) {

        Optional<BookReleaseEntity> bookReleaseByBookUuidOptional = bookReleaseRepository
                .findBookReleaseByBookReleaseUuid(uuid);

        if (bookReleaseByBookUuidOptional.isPresent()) {
            BookReleaseEntity bookRelease = bookReleaseByBookUuidOptional.get();
            Optional<BookEntity> bookEntityByIdOptional = bookRepository
                    .findBookEntityById(bookRelease.getBook().getId());

            if (bookEntityByIdOptional.isPresent()) {
                BookEntity book = bookEntityByIdOptional.get();
                return bookDtoMapper.toBooksDetailsDto(book, bookRelease);
            }
        }
        throw new ResourceNotFoundException(
                "not found"
        );
    }
}

