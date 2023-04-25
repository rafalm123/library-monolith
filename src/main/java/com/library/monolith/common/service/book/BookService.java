package com.library.monolith.common.service.book;

import com.library.monolith.common.model.dto.book.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookDetailsDTO getBookDetailsDto(Long id);

    BookOverviewDTO getBookOverviewDto(Long id);

    List<BookOverviewDTO> getBookOverviewDtoList();

    String addBook (BookCreateDTO bookCreateDTO);

    String deleteBook (BookDeleteDTO bookDeleteDTO);

}
