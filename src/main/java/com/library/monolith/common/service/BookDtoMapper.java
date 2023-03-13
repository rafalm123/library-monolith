package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class BookDtoMapper implements BookDtoMapperMapStruct{

    public BookDtoMapper() {
    }

    @Override
    public BookDetailsDTO dtoMapping(BookEntity book, BookReleaseEntity bookRelease){

            return new BookDetailsDTO(
                    (book.getAuthor()),
                    (book.getTitle()),
                    (book.getBookPublicationYear()),
                    (bookRelease.getPages()),
                    (bookRelease.getIsbn()),
                    (bookRelease.getPublisherReleaseYear()),
                    (bookRelease.getLanguage())
            );
    }
}
