package com.library.monolith.common.service;

import com.library.monolith.common.model.entity.BookEntity;
import com.library.monolith.common.model.entity.BookReleaseEntity;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookReleaseRepository bookReleaseRepository;
    @Mock
    private BookDtoDetailsMapper bookDtoDetailsMapper;

    private BookService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new BookService(bookRepository,bookReleaseRepository);
    }

    @Test
    void itShouldFindBookReleaseByUUID() {
        //GIVEN
        UUID uuid = UUID.randomUUID();
        BookReleaseEntity bookRelease = new BookReleaseEntity();
        bookRelease.setBookReleaseUuid(uuid);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookRelease.setBook(bookEntity);
        BookDetailsDTO bookDetailsDTO = new BookDetailsDTO();
        //WHEN
        Mockito.when(bookReleaseRepository.findBookReleaseByBookReleaseUuid(uuid))
                .thenReturn(Optional.of(bookRelease));
        Mockito.when(bookRepository.findBookEntityById(bookEntity.getId()))
                .thenReturn(Optional.of(bookEntity));
        Mockito.when(bookDtoDetailsMapper.toBookDetailsDto(bookEntity,bookRelease))
                .thenReturn(bookDetailsDTO);
        //THEN

        BookDetailsDTO result = underTest.getBookDetailsDto(uuid);

        assertEquals(result,bookDetailsDTO);
    }

    @Test
    void itShouldThrowWhenBookReleaseNotFound() {
        //GIVEN
        UUID uuid = UUID.randomUUID();
        BookReleaseEntity bookRelease = new BookReleaseEntity();
        bookRelease.setBookReleaseUuid(uuid);
        //WHEN
        given(bookReleaseRepository.findBookReleaseByBookReleaseUuid(uuid))
                .willReturn(Optional.empty());

        //THEN
        assertThatThrownBy(() -> underTest.getBookDetailsDto(uuid))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found");
    }
}