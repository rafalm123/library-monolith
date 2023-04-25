package com.library.monolith.common.service.book;

import com.library.monolith.common.exception.book.BookError;
import com.library.monolith.common.exception.book.BookException;
import com.library.monolith.common.mapping.book.BookCreateDtoMapper;
import com.library.monolith.common.mapping.book.BookOverviewDtoMapper;
import com.library.monolith.common.model.dto.book.*;
import com.library.monolith.common.model.entity.book.Book;
import com.library.monolith.common.model.entity.book.BookRelease;
import com.library.monolith.common.model.entity.book.ReleaseCopy;
import com.library.monolith.common.model.entity.book.ReleaseCopyVersion;
import com.library.monolith.common.repository.book.BookReleaseRepository;
import com.library.monolith.common.mapping.book.BookDetailsDtoMapper;
import com.library.monolith.common.repository.book.BookRepository;
import com.library.monolith.common.repository.book.ReleaseCopyRepository;
import com.library.monolith.common.repository.book.ReleaseCopyVersionRepository;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImplementation implements BookService {
    private final BookReleaseRepository bookReleaseRepository;
    private final BookRepository bookRepository;
    private final ReleaseCopyRepository releaseCopyRepository;
    private final ReleaseCopyVersionRepository releaseCopyVersionRepository;
    private final LibraryUserRepository libraryUserRepository;

    public BookDetailsDTO getBookDetailsDto(Long id) {
          val bookRelease = bookReleaseRepository.findById(id).orElseThrow(() -> {
            throw new BookException(BookError.BOOK_RELEASE_NOT_FOUND);
        });

        return BookDetailsDtoMapper.getInstance().toBookDetailsDto(bookRelease.getBook(), bookRelease);
    }

    public BookOverviewDTO getBookOverviewDto(Long id) {
        val bookRelease = bookReleaseRepository.findById(id).orElseThrow(() -> {
            throw new BookException(BookError.BOOK_RELEASE_NOT_FOUND);
        });

        return BookOverviewDtoMapper.getInstance().toBookOverviewDto(bookRelease.getBook(), bookRelease);
    }

    public List<BookOverviewDTO> getBookOverviewDtoList(){
        List<BookRelease> bookReleases = bookReleaseRepository.findAll();

        return bookReleases.stream().map(bookRelease ->
             BookOverviewDtoMapper.getInstance().toBookOverviewDto(bookRelease.getBook(), bookRelease)
        ).collect(Collectors.toList());
    }

    public String addBook(BookCreateDTO bookCreateDTO){
        BookCreateDtoMapper getInstance = BookCreateDtoMapper.getInstance;
        Book book = getInstance.toBook(bookCreateDTO);
        if (bookRepository.findByAuthorAndTitle(book.getAuthor(),book.getTitle()).isEmpty()) {
            bookRepository.save(book);
        }
        BookRelease bookRelease = getInstance.toBookRelease(bookCreateDTO, book);
        bookRelease.setBook(book);
        bookReleaseRepository.save(bookRelease);

        ReleaseCopy releaseCopy = getInstance.toReleaseCopy(bookCreateDTO, bookRelease);
        releaseCopy.setBookRelease(bookRelease);
        releaseCopyRepository.save(releaseCopy);

        ReleaseCopyVersion releaseCopyVersion = getInstance.toReleaseCopyVersion(bookCreateDTO, releaseCopy);
        releaseCopyVersion.setReleaseCopy(releaseCopy);
        releaseCopyVersionRepository.save(releaseCopyVersion);

        return bookCreateDTO.getAuthor() + " " + bookCreateDTO.getTitle() + " was successfully added.";
    }


    public String deleteBook(BookDeleteDTO bookDeleteDTO){

        Book book = bookRepository.findByAuthorAndTitle(bookDeleteDTO.getAuthor(), bookDeleteDTO.getTitle())
                .orElseThrow(() -> new BookException(BookError.BOOK_RELEASE_NOT_FOUND));

        bookRepository.delete(book);

        return book.getAuthor() + " " + book.getTitle() + " has been deleted.";
    }
}
