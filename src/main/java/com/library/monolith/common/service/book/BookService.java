package com.library.monolith.common.service.book;

import com.library.monolith.common.model.dto.book.BookCreateDTO;
import com.library.monolith.common.model.dto.book.BookDetailsDTO;
import com.library.monolith.common.model.dto.book.BookOverviewDTO;
import com.library.monolith.common.model.dto.book.BookOverviewQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookDetailsDTO getBookDetailsDto(Long id);

    BookOverviewDTO getBookOverviewDto(Long id);

    List<BookOverviewDTO> getBookOverviewDtoList();

    Page<BookOverviewDTO> getBookOverviewPage(BookOverviewQueryDTO queryDto);

    BookDetailsDTO addBook (BookCreateDTO bookCreateDTO);


}
