package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import com.library.monolith.common.model.entity.ReleaseCopyEntity;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public BookDetailsDTO getBookDTOByReleaseUuid(UUID uuid) {

        Optional<BookReleaseEntity> bookReleaseByBookUuidOptional = bookReleaseRepository
                .findBookReleaseByBookReleaseUuid(uuid);

        if (bookReleaseByBookUuidOptional.isPresent()) {
            BookReleaseEntity bookRelease = bookReleaseByBookUuidOptional.get();
            Optional<BookEntity> bookEntityByIdOptional = bookRepository
                    .findBookEntityById(bookRelease.getBook().getId());

            if (bookEntityByIdOptional.isPresent()) {
                BookEntity book = bookEntityByIdOptional.get();
                return bookDtoMapper.dtoMapping(book, bookRelease);
            }
        }
        throw new ResourceNotFoundException(
                "not found"
        );
    }

    public List<BookDetailsDTO> getListOfBooks(){

        List<BookEntity> books = bookRepository.findAll();
        List<BookReleaseEntity> releases = bookReleaseRepository.findAll();

        List<BookDetailsDTO> bookDetailsDTOS = books.stream()
                .map(book -> {
                    BookDetailsDTO bookDetailsDTO = new BookDetailsDTO();
                    bookDetailsDTO.setAuthor(book.getAuthor());
                    bookDetailsDTO.setTitle(book.getTitle());
                    bookDetailsDTO.setBookPublicationYear(book.getBookPublicationYear());

                    releases.stream()
                            .filter(bookRelease -> Objects.equals(bookRelease.getBook().getId(), book.getId()))
                            .findFirst()
                            .ifPresent(bookRelease -> {
                                bookDetailsDTO.setPages(bookRelease.getPages());
                                bookDetailsDTO.setIsbn(bookRelease.getIsbn());
                                bookDetailsDTO.setPublisherReleaseYear(bookRelease.getPublisherReleaseYear());
                                bookDetailsDTO.setLanguage(bookRelease.getLanguage());
                            });
                    return bookDetailsDTO;
                })
                .toList();
        return bookDetailsDTOS;
    }
}

