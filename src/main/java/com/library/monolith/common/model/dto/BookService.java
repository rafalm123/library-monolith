package com.library.monolith.common.model.dto;

import com.library.monolith.common.model.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookDetailsDTO getBookDetailsDto(Long id);

    BookOverviewDTO getBookOverviewDto(Long id);

    List<BookOverviewDTO> getBookOverviewDtoList();

    Page<BookOverviewDTO> getBookOverviewPage(BookOverviewQueryDto queryDto);

}