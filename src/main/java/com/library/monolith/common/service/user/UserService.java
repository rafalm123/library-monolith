package com.library.monolith.common.service.user;


import com.library.monolith.common.model.dto.user.LibraryUserDetailsDTO;
import com.library.monolith.common.model.dto.user.LibraryUserOverviewDTO;

import java.util.List;

public interface UserService {

    LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id);

    LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id);

    List<LibraryUserOverviewDTO> getLibraryUserOverviewDtoList();

//    Page<LibraryUserOverviewDTO> getLibraryUserOverviewPage();
}
