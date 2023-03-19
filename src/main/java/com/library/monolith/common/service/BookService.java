package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookReleaseRepository bookReleaseRepository;
    private final BookDtoDetailsMapper bookDtoDetailsMapper;
    private final BookDtoOverviewMapper bookDtoOverviewMapper;

    public BookService(BookRepository bookRepository, BookReleaseRepository bookReleaseRepository) {
        this.bookRepository = bookRepository;
        this.bookReleaseRepository = bookReleaseRepository;
        this.bookDtoDetailsMapper = new BookDtoDetailsMapper() {
            @Override
            public BookDetailsDTO toBookDetailsDto(BookEntity book, BookReleaseEntity release) {
                return new BookDetailsDTO();
            }
        };
        this.bookDtoOverviewMapper = new BookDtoOverviewMapper() {
            @Override
            public BookDtoOverview toBookOverviewDto(BookEntity book, BookReleaseEntity release) {
                return new BookDtoOverview();
            }
        };
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
                return bookDtoDetailsMapper.toBookDetailsDto(book, bookRelease);
            }
        }
        throw new ResourceNotFoundException(
                "not found"
        );
    }

    public List<BookDtoOverview> getBooksOverview(Pageable pageable) {

        Page<BookReleaseEntity> releaseCollection = bookReleaseRepository.findAll(pageable);

        List<BookDtoOverview> BookOverviewDtos = releaseCollection.stream()
                .map(release -> bookDtoOverviewMapper.toBookOverviewDto(bookCollection.stream().anyMatch(book ->{
                    book.getId().equals(release.getBook().getId());
                    return book;
                }), release))
                .collect(Collectors.toList());
        return BookOverviewDtos;
    }
}




//        List<BookDtoOverview> bookDtoOverviews = books.stream()
//                .map(
//                        book -> {
//
//                            BookDtoOverview bookDtoOverview = new BookDtoOverview();
//                            releases.stream()
//                                    .filter(bookRelease -> Objects.equals(bookRelease.getBook().getId(), book.getId()))
//                                    .findFirst()
//                                    .ifPresent(
//                                            bookRelease -> {
//                                                bookDtoOverview.
//                                            });
//
//                        })
//                        }
//
//        public List<BookDtoOverview> getBooksOverview() {
//
//            List<BookReleaseEntity> releases = bookReleaseRepository.findAll();
//            List<BookEntity> books = bookRepository.findAll();
//            List<BookDtoOverview> overviews = new ArrayList<>();
//
//            for (BookEntity book: books) {
//
//
//                if (book.getId().equals())
//
//
//            }
//
//        }



