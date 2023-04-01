package com.library.monolith.common.service;

import com.library.monolith.common.mapping.book.BookDetailsDtoMapper;
import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BookOverviewDtoMapperTest {

    private Book book;
    private BookRelease bookRelease;

    @InjectMocks
    private BookDetailsDtoMapper underTest;


    @Test
    void itShouldMapBookAndBookReleaseToDtoBook() {
        //GIVEN
        Book book = new Book();
        book.setAuthor("Author");
        book.setTitle("title");
        book.setPublishYear(2023);

        BookRelease bookRelease=new BookRelease();
        bookRelease.setPages(1111);
        bookRelease.setIsbn(12345L);
        bookRelease.setReleaseYear(1333);
        bookRelease.setLanguage("English");

        BookDetailsDTO bookDto = new BookDetailsDTO(
                book.getAuthor(),
                book.getTitle(),
                book.getPublishYear(),
                bookRelease.getPages(),
                bookRelease.getIsbn(),
                bookRelease.getReleaseYear(),
                bookRelease.getLanguage()
        );
        //WHEN
        BookDetailsDTO bookDetailsDTO = underTest.toBookDetailsDto(book, bookRelease);
        //THEN
        assertThat(bookDetailsDTO).isEqualTo(bookDto);
    }
}