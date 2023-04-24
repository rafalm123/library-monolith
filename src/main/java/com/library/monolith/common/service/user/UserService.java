package com.library.monolith.common.service.user;


import com.library.monolith.common.model.dto.user.*;

import java.util.List;

public interface UserService {

    LibraryUserDetailsDTO getLibraryUserDetailsDto(Long id);

    LibraryUserOverviewDTO getLibraryUserOverviewDto(Long id);

    List<LibraryUserOverviewDTO> getLibraryUserOverviewDtoList();

//    Page<LibraryUserOverviewDTO> getLibraryUserOverviewPage();

    LibraryUserOverviewDTO registerUser(LibraryUserRegistrationDTO registrationDTO);

    String deleteUser (LibraryUserDeleteDTO deleteDTO);

    String editUser(String username, LibraryUserUpdateDTO libraryUserUpdateDTO);
}
