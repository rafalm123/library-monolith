package com.library.monolith.common.service.book;

import com.library.monolith.common.exception.book.BookError;
import com.library.monolith.common.exception.book.BookException;
import com.library.monolith.common.model.dto.book.BookCreateDTO;
import com.library.monolith.common.model.dto.book.BookDeleteDTO;
import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import com.library.monolith.common.model.entity.book.ReleaseCopy;
import com.library.monolith.common.model.entity.book.ReleaseCopyVersion;
import com.library.monolith.common.repository.book.BookReleaseRepository;
import com.library.monolith.common.repository.book.BookRepository;
import com.library.monolith.common.repository.book.ReleaseCopyRepository;
import com.library.monolith.common.repository.book.ReleaseCopyVersionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplementationTest {

    @InjectMocks
    private BookServiceImplementation bookService;

    @Mock
    private BookReleaseRepository bookReleaseRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ReleaseCopyRepository releaseCopyRepository;
    @Mock
    private ReleaseCopyVersionRepository releaseCopyVersionRepository;
    @Mock

    private BookRelease bookRelease;
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(1L);
        book.setAuthor("Author");
        book.setTitle("Title");
        book.setPublishYear(2023);

        bookRelease = new BookRelease();
        bookRelease.setId(2L);
        bookRelease.setIsbn(1234567890123L);
        bookRelease.setPages(200);
        bookRelease.setReleaseYear(2023);
        bookRelease.setLanguage("English");
        bookRelease.setBook(book);
    }

    @Test
    public void itShouldGetBookDetailsDto() {
        when(bookReleaseRepository.findById(1L)).thenReturn(Optional.of(bookRelease));

        BookDetailsDTO bookDetailsDTO = bookService.getBookDetailsDto(1L);

        assertThat(bookDetailsDTO).isNotNull();
        assertThat(bookDetailsDTO.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(bookDetailsDTO.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookDetailsDTO.getIsbn()).isEqualTo(bookRelease.getIsbn());
        assertThat(bookDetailsDTO.getPages()).isEqualTo(bookRelease.getPages());
    }

    @Test
    public void itShouldThrowBookExceptionWhenBookReleaseNotFound() {
        when(bookReleaseRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getBookDetailsDto(2L))
                .isInstanceOf(BookException.class)
                .satisfies(exception -> {
                    assertThat(((BookException) exception).getBookError())
                            .isEqualTo(BookError.BOOK_RELEASE_NOT_FOUND);
                });
    }

    @Test
    public void shouldAddBook() {
        BookCreateDTO bookCreateDTO = new BookCreateDTO("Author", "Title",
                2023, 1234567890123L,
                200, 2023,
                "Polish", "Hardcover",
                "Available", "Notes");

        when(bookRepository.findByAuthorAndTitle("Author", "Title"))
                .thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookReleaseRepository.save(any(BookRelease.class))).thenReturn(bookRelease);

        String result = bookService.addBook(bookCreateDTO);

        assertThat(result).isNotBlank();
        assertThat(result).isEqualTo(bookCreateDTO.getAuthor() + " " +
                bookCreateDTO.getTitle() + " was successfully added.");

        verify(bookRepository).save(any(Book.class));
        verify(bookReleaseRepository).save(any(BookRelease.class));
        verify(releaseCopyRepository).save(any(ReleaseCopy.class));
        verify(releaseCopyVersionRepository).save(any(ReleaseCopyVersion.class));
    }

    @Test
    public void shouldDeleteBook() {
        BookDeleteDTO bookDeleteDTO = new BookDeleteDTO("Title", "Author");

        when(bookRepository.findByAuthorAndTitle("Author", "Title"))
                .thenReturn(Optional.of(book));

        String result = bookService.deleteBook(bookDeleteDTO);

        assertThat(result).isNotBlank();
        assertThat(result).isEqualTo(bookDeleteDTO.getAuthor() + " " +
                 bookDeleteDTO.getTitle() + " has been deleted.");

        verify(bookRepository).delete(book);
    }

    @Test
    public void shouldThrowBookExceptionWhenBookNotFound() {
        BookDeleteDTO bookDeleteDTO = new BookDeleteDTO("Title", "Author");

        when(bookRepository.findByAuthorAndTitle("Author", "Title"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.deleteBook(bookDeleteDTO))
                .isInstanceOf(BookException.class)
                .satisfies(exception -> {
                    assertThat(((BookException) exception).getBookError())
                            .isEqualTo(BookError.BOOK_RELEASE_NOT_FOUND);
                });
    }
}
