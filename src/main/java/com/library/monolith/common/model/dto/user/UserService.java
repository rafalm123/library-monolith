package com.library.monolith.common.model.dto.user;

import com.library.monolith.common.mapping.book.BookOverviewDtoMapper;
import com.library.monolith.common.mapping.user.LibraryUserOverviewDtoMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id) throws Exception;

    LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id);

    List<LibraryUserOverviewDTO> getLibraryUserOverviewDtoList();

//    Page<LibraryUserOverviewDTO> getLibraryUserOverviewPage();

}
