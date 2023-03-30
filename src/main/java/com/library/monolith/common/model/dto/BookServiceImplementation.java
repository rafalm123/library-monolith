package com.library.monolith.common.model.dto;

import com.library.monolith.common.exception.BookError;
import com.library.monolith.common.exception.BookException;
import com.library.monolith.common.mapping.BookOverviewDtoMapper;
import com.library.monolith.common.model.entity.Book;
import com.library.monolith.common.model.entity.BookRelease;
import com.library.monolith.common.repository.BookReleaseRepository;
import com.library.monolith.common.mapping.BookDetailsDtoMapper;
import com.library.monolith.common.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookService{
    private final BookReleaseRepository bookReleaseRepository;

    public BookDetailsDTO getBookDetailsDto(Long id) {
          val bookRelease = bookReleaseRepository.findById(id).orElseThrow(() -> {
            throw new BookException(BookError.BOOK_RELASE_NOT_FOUND);
        });

        return BookDetailsDtoMapper.getInstance().toBookDetailsDto(bookRelease.getBook(), bookRelease);
    }

    public BookOverviewDTO getBookOverviewDto(Long id) {
        val bookRelease = bookReleaseRepository.findById(id).orElseThrow(() -> {
            throw new BookException(BookError.BOOK_RELASE_NOT_FOUND);
        });

        return BookOverviewDtoMapper.getInstance().toBookOverviewDto(bookRelease.getBook(), bookRelease);
    }

    public List<BookOverviewDTO> getBookOverviewDtoList(){
        List<BookRelease> bookReleases = bookReleaseRepository.findAll();

        return bookReleases.stream().map(bookRelease ->
             BookOverviewDtoMapper.getInstance().toBookOverviewDto(bookRelease.getBook(), bookRelease)
        ).collect(Collectors.toList());
    }

    public Page<BookOverviewDTO> getBookOverviewPage(BookOverviewQueryDto queryDto){
        List<BookOverviewDTO> list = getBookOverviewDtoList();
        return new PageImpl<>(list,PageRequest.of(queryDto.page, queryDto.pageSize), list.size());
    }

}
