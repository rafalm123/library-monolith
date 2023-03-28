package com.library.monolith.common.service;

import com.library.monolith.common.exception.BookException;
import com.library.monolith.common.mapping.BookDetailsDtoMapper;
import com.library.monolith.common.model.dto.BookDetailsDTO;
import com.library.monolith.common.model.dto.BookServiceImplementation;
import com.library.monolith.common.model.entity.Book;
import com.library.monolith.common.model.entity.BookRelease;
import com.library.monolith.common.repository.BookReleaseRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookServiceImplementationTest {

    @Mock private BookReleaseRepository bookReleaseRepository;
    @Mock private BookRelease bookReleaseEntityMock;
    @Mock private Book bookEntityMock;
    @Mock private BookDetailsDtoMapper bookDetailsDtoMapper;
    @Mock BookDetailsDTO mapperResult;

    @InjectMocks
    private BookServiceImplementation service;

    @Test
    void should_find_single_book() {
        //given
        given(bookReleaseRepository.findById(anyLong())).willReturn(Optional.of(bookReleaseEntityMock));
        given(bookReleaseEntityMock.getBook()).willReturn(bookEntityMock);
        given(bookDetailsDtoMapper.toBookDetailsDto(bookEntityMock, bookReleaseEntityMock)).willReturn(mapperResult);

        //when
        val result = service.getBookDetailsDto(1L);

        //then
        assertEquals(result, mapperResult);
    }

    @Test
    void should_not_find_book_and_throw_exception() {
        assertThatThrownBy(() -> service.getBookDetailsDto(any()))
                .isInstanceOf(BookException.class);
    }
}