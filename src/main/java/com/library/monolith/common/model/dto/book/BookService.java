package com.library.monolith.common.model.dto.book;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookDetailsDTO getBookDetailsDto(Long id);

    BookOverviewDTO getBookOverviewDto(Long id);

    List<BookOverviewDTO> getBookOverviewDtoList();

    Page<BookOverviewDTO> getBookOverviewPage(BookOverviewQueryDTO queryDto);

    BookDetailsDTO addBook (BookCreateDTO bookCreateDTO);


}
