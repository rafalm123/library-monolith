package com.library.monolith.common.controller.book;

import com.library.monolith.common.model.dto.book.BookCreateDTO;
import com.library.monolith.common.model.dto.book.BookDeleteDTO;
import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.service.book.BookServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @Mock
    private BookServiceImplementation bookServiceImplementation;

    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController(bookServiceImplementation);
    }

    @Test
    void testGetBookDetailsDtoByReleaseId() {
        Long bookId = 1L;
        BookDetailsDTO expectedBookDetailsDTO = new BookDetailsDTO();

        when(bookServiceImplementation.getBookDetailsDto(bookId)).thenReturn(expectedBookDetailsDTO);

        ResponseEntity<BookDetailsDTO> responseEntity = bookController.getBookDetailsDtoByReleaseId(bookId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedBookDetailsDTO, responseEntity.getBody());
    }

    @Test
    void testGetAllBookOverviewDtos() {
        BookOverviewDTO bookOverviewDTO1 = new BookOverviewDTO();
        BookOverviewDTO bookOverviewDTO2 = new BookOverviewDTO();
        List<BookOverviewDTO> expectedBookOverviewDTOList = Arrays.asList(bookOverviewDTO1, bookOverviewDTO2);

        when(bookServiceImplementation.getBookOverviewDtoList()).thenReturn(expectedBookOverviewDTOList);

        ResponseEntity<List<BookOverviewDTO>> responseEntity = bookController.getAllBookOverviewDtos();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedBookOverviewDTOList, responseEntity.getBody());
    }

    @Test
    void testAddBook() {
        BookCreateDTO bookCreateDTO = new BookCreateDTO();
        String expectedResponse = "Book added successfully";

        when(bookServiceImplementation.addBook(any(BookCreateDTO.class))).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = bookController.addBook(bookCreateDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testDeleteBook() {
        BookDeleteDTO bookDeleteDTO = new BookDeleteDTO();
        String expectedResponse = "Book deleted successfully";

        when(bookServiceImplementation.deleteBook(any(BookDeleteDTO.class))).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = bookController.deleteBook(bookDeleteDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}
