package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookReleaseRepository bookReleaseRepository;
    private BookDtoMapper bookDtoMapper;

    public BookDetailsDTO getBookDTOByReleaseUuid(UUID uuid) {

        Optional<BookReleaseEntity> bookReleaseByBookUuidOptional = bookReleaseRepository.findBookReleaseByBookReleaseUuid(uuid);

        if (bookReleaseByBookUuidOptional.isPresent()) {
            BookReleaseEntity bookRelease = bookReleaseByBookUuidOptional.get();
            Optional<BookEntity> bookEntityByIdOptional = bookRepository
                    .findBookEntityById(bookRelease.getBookById().getId());

            if (bookEntityByIdOptional.isPresent()) {
                BookEntity book = bookEntityByIdOptional.get();

                return bookDtoMapper.dtoMapping(book, bookRelease);
            }
        }
        return null;
    }
}
