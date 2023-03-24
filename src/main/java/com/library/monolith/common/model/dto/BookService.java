package com.library.monolith.common.model.dto;

import com.library.monolith.common.exception.BookError;
import com.library.monolith.common.exception.BookException;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.mapping.BookDetailsDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookReleaseRepository bookReleaseRepository;

    public BookDetailsDTO getBookDetailsDto(Long id) {
          val bookRelease = bookReleaseRepository.findById(id).orElseThrow(() -> {
            throw new BookException(BookError.BOOK_RELASE_NOT_FOUND);
        });

        return BookDetailsDtoMapper.getInstance().toBookDetailsDto(bookRelease.getBook(), bookRelease);
    }
}
