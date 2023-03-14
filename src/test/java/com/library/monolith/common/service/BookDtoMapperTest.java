package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BookDtoMapperTest {

    private BookEntity book;
    private BookReleaseEntity bookRelease;
    private BookDtoMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = new BookDtoMapper();
    }


    @Test
    void itShouldMapBookAndBookReleaseToDtoBook() {
        //GIVEN
        BookEntity book = new BookEntity();
        book.setAuthor("Author");
        book.setTitle("title");
        book.setBookPublicationYear(2023L);

        BookReleaseEntity bookRelease=new BookReleaseEntity();
        bookRelease.setPages(1111L);
        bookRelease.setIsbn(12345L);
        bookRelease.setPublisherReleaseYear(1333L);
        bookRelease.setLanguage("English");

        BookDetailsDTO bookDto = new BookDetailsDTO(
                book.getAuthor(),
                book.getTitle(),
                book.getBookPublicationYear(),
                bookRelease.getPages(),
                bookRelease.getIsbn(),
                bookRelease.getPublisherReleaseYear(),
                bookRelease.getLanguage()
        );
        //WHEN
        BookDetailsDTO bookDetailsDTO = underTest.toBooksDetailsDto(book, bookRelease);
        //THEN
        assertThat(bookDetailsDTO).isEqualTo(bookDto);
    }
}