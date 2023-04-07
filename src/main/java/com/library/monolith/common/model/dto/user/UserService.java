package com.library.monolith.common.model.dto.user;


import java.util.List;

public interface UserService {

    LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id);

    LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id);

    List<LibraryUserOverviewDTO> getLibraryUserOverviewDtoList();

//    Page<LibraryUserOverviewDTO> getLibraryUserOverviewPage();
}
