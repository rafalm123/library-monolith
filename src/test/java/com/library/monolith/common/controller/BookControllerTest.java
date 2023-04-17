package com.library.monolith.common.controller;

import com.library.monolith.common.controller.book.BookController;
import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.dto.book.BookServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookServiceImplementation bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetBookDetailsDtoById() {
        // Given
        Long bookId = 1L;
        BookDetailsDTO expectedBookDetailsDTO = new BookDetailsDTO();

        when(bookService.getBookDetailsDto(bookId)).thenReturn(expectedBookDetailsDTO);

        // When
        ResponseEntity<BookDetailsDTO> responseEntity = bookController.getBookDetailsDtoById(bookId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expectedBookDetailsDTO);
    }

    @Test
    public void testGetAllBookOverviewDtos() {
        // Given
        List<BookOverviewDTO> expectedBookOverviewDTOs = new ArrayList<>();
        expectedBookOverviewDTOs.add(new BookOverviewDTO());

        when(bookService.getBookOverviewDtoList()).thenReturn(expectedBookOverviewDTOs);

        // When
        ResponseEntity<List<BookOverviewDTO>> responseEntity = bookController.getAllBookOverviewDtos();

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expectedBookOverviewDTOs);
    }

}